/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Vehicule;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Rania2
 */
public class ServiceVehicule {

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
ArrayList<Vehicule> vehicules;
    ConnectionRequest req;

    public boolean resultOk;
    //2  creer un attribut de type de la classe en question (static)
    public static ServiceVehicule instance = null;

    //Singleton => Design Pattern qui permet de creer une seule instance d'un objet 
    //1 rendre le constructeur private
    private ServiceVehicule() {
        req = new ConnectionRequest();
    }

    //3 la methode qu'elle va ramplacer le constructeur 
    public static ServiceVehicule getinstance() {
        if (instance == null) {
            instance = new ServiceVehicule();
        }
        return instance;
    }

    //methode d'ajout
    public boolean addVehicule(Vehicule t) {
        String image=t.getImg();
        String modele = t.getModele();
        String matricule = t.getMatricule();
        String type=t.getType();
        int puissance=t.getPuissance();
        int batterie=t.getBatterie();
        int prix=t.getPrix();
        String status = t.getStatus();

        String url = Statics.BASE_URL + "/vehicule/newJSON?"+"image="+image + "&modele=" + modele + "&matricule=" + matricule + "&type=" + type +"&puissance="+puissance+"&batterie="+batterie+"&prix="+prix+"&status="+status;
        req.setUrl(url);
        //GET =>
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //si le code return 200 
                //
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }

public void parseVehicules(String jsonText, ArrayList<Vehicule> vehicules) {
    if (jsonText == null) {
        System.err.println("Error: jsonText is null");
        return;
    }

    try {
    JSONParser j = new JSONParser();
    Map<String, Object> vehicleListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
    if (!vehicleListJson.containsKey("root")) {
        System.err.println("Error: JSON data is not in the expected format");
        return;
    }

    java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) vehicleListJson.get("root");
    for (Map<String, Object> obj : list) {
        System.out.println("obj = " + obj);
        Vehicule v = new Vehicule();
        float id = Float.parseFloat(obj.get("idvehicule").toString());
        v.setId((int) id);
        v.setType(obj.get("type").toString());
        v.setModele(obj.get("modele").toString());
        Object batterieObj = obj.get("batterie");
        if (batterieObj instanceof Number) {
            v.setBatterie(((Number) batterieObj).intValue());
        } else {
            // handle error, e.g. set a default value
            v.setBatterie(0);
        }
        v.setMatricule(obj.get("matricule").toString());
        v.setStatus(obj.get("status").toString());
        Object prixObj = obj.get("prix");
        if (prixObj instanceof Number) {
            v.setPrix(((Number) prixObj).intValue());
        } else {
            // handle error, e.g. set a default value
            v.setPrix(0);
        }
        v.setMatricule(obj.get("matricule").toString());
        Object puissanceObj = obj.get("puissance");
        if (puissanceObj instanceof Number) {
            v.setPuissance(((Number) puissanceObj).intValue());
        } else {
            // handle error, e.g. set a default value
            v.setPuissance(0);
        }
        v.setImg(obj.get("image").toString());
        vehicules.add(v);
    }

} catch (IOException ex) {
    System.err.println("Error: " + ex.getMessage());
}
}



//a
public ArrayList<Vehicule> getAllVehicules() {
    ArrayList<Vehicule> vehicules = new ArrayList<>();
    String url ="http://127.0.0.1:8000/vehicule/JSON";
    try {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(con.getResponseData());
                parseVehicules(jsonText, vehicules);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return vehicules;
}

    public boolean deleteVehicules(String id ) {
        
        String url = Statics.BASE_URL +"/vehicule/deleteJSON/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
   public boolean modifierVehicule(Vehicule p,String id) {
        String url = Statics.BASE_URL +"/vehicule/editJSON/"+id+ "?type=" + p.getType() +"&prix="+p.getPrix()+"&puissance="+p.getPuissance();
                                     //  vehicule/editJSON/135?type=trotinette&prix=20&puissance=10
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
   
   public ArrayList<Vehicule> getVehicule(int id) {
    ArrayList<Vehicule> vehicules = new ArrayList<>();
    String url ="http://127.0.0.1:8000/vehicule/showJSON/"+id;
    try {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(con.getResponseData());
                parseVehicules(jsonText, vehicules);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return vehicules;
}
}
