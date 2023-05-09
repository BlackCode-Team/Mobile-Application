package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Badge;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ServiceBadge {
    
    ArrayList<Badge> badge;
    ConnectionRequest req;

    public boolean resultOk;
    //2  creer un attribut de type de la classe en question (static)
    public static ServiceBadge instance = null;

    //Singleton => Design Pattern qui permet de creer une seule instance d'un objet 
    //1 rendre le constructeur private
    private ServiceBadge() {
        req = new ConnectionRequest();
    }

    //3 la methode qu'elle va ramplacer le constructeur 
    public static ServiceBadge getinstance() {
        if (instance == null) {
            instance = new ServiceBadge();
        }
        return instance;
    }

    //methode d'ajout
    public boolean addBadge(Badge b) {
        String typebadge = b.getTypebadge();
        int nbpoint = b.getNbpoint();

        String url = Statics.BASE_URL+"/ajouterBadgeJson/new?typebadge="+typebadge +"&nbpoint=" +nbpoint;
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

public void parseBadges(String jsonText, ArrayList<Badge> badges) {
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
            Badge b = new Badge();
            float id = Float.parseFloat(obj.get("idbadge").toString());
            b.setId((int) id);
            
            b.setTypebadge(obj.get("typebadge").toString());
            float nbpoint = Float.parseFloat(obj.get("nbpoint").toString());
            b.setNbpoint((int) nbpoint);
           
           
            badges.add(b);
        }

    } catch (IOException ex) {
        System.err.println("Error: " + ex.getMessage());
    }
}



//a
public ArrayList<Badge> getAllBadges() {
    ArrayList<Badge> badges = new ArrayList<>();
    String url ="http://127.0.0.1:8000/allBadges";
    try {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(con.getResponseData());
                parseBadges(jsonText,badges);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return badges;
}

    public boolean deleteBadge(String id ) {
        
        String url = Statics.BASE_URL +"/supprimerBadgeJson/"+id;
        
        
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
    
   public boolean modifierBadge(Badge b,String id) {
        String url = Statics.BASE_URL +"/modifierBadgeJSON/"+id+"?typebadge=" + b.getTypebadge()+ "&nbpoint=" + b.getNbpoint();
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
