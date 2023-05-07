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
import com.mycompany.entities.Park;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Andrew
 */
public class ServicePark {

    ArrayList<Park> parks;
    ConnectionRequest req;

    public boolean resultOk;
    //2  creer un attribut de type de la classe en question (static)
    public static ServicePark instance = null;

    //Singleton => Design Pattern qui permet de creer une seule instance d'un objet 
    //1 rendre le constructeur private
    private ServicePark() {
        req = new ConnectionRequest();
    }

    //3 la methode qu'elle va ramplacer le constructeur 
    public static ServicePark getinstance() {
        if (instance == null) {
            instance = new ServicePark();
        }
        return instance;
    }

    //methode d'ajout
    public boolean addPark(Park t) {
        String nom = t.getNom();
        String ville = t.getVille();
        String statut = t.getStatut();
        int nbspot = t.getNbspot();

        String url = Statics.BASE_URL + "/park/addparkJSON/new?" + "nom=" + nom + "&ville=" + ville + "&nbspot=" + nbspot + "&statut=" + statut;

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

public void parseParks(String jsonText, ArrayList<Park> parks) {
    if (jsonText == null) {
        System.err.println("Error: jsonText is null");
        return;
    }

    try {
        JSONParser j = new JSONParser();
        Map<String, Object> parksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        if (!parksListJson.containsKey("root")) {
            System.err.println("Error: JSON data is not in the expected format");
            return;
        }

        java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) parksListJson.get("root");
        for (Map<String, Object> obj : list) {
            System.out.println("obj = " + obj);
            Park t = new Park();
            float id = Float.parseFloat(obj.get("idpark").toString());
            t.setIdpark((int) id);
            Object nbspotObj = obj.get("nbspot");
            if (nbspotObj instanceof Number) {
                t.setNbspot(((Number) nbspotObj).intValue());
            } else {
                // handle error, e.g. set a default value
                t.setNbspot(0);
            }
            t.setStatut(obj.get("statut").toString());
            t.setVille(obj.get("ville").toString());
            t.setNom(obj.get("nom").toString());
            parks.add(t);
        }

    } catch (IOException ex) {
        System.err.println("Error: " + ex.getMessage());
    }
}



//a
public ArrayList<Park> getAllParks() {
    ArrayList<Park> parks = new ArrayList<>();
    String url ="http://127.0.0.1:8000/park/jsonall";
    try {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(con.getResponseData());
                parseParks(jsonText, parks);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return parks;
}

    public boolean deletePark(String id ) {
        
        String url = Statics.BASE_URL +"/park/deleteparksJSON/"+id;
        
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
   public boolean modifierPark(Park p,String id) {
        String url = Statics.BASE_URL +"/park/updateparkJSON/"+id+"?nom=" + p.getNom() + "&ville=" + p.getVille() + "&nbspot=" + p.getNbspot() + "&statut=" + p.getStatut();
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
}
