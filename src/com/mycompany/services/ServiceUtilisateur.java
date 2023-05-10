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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Park;
import com.mycompany.entities.Utilisateur;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jokser
 */
public class ServiceUtilisateur {

    ArrayList<Utilisateur> users;
    ConnectionRequest req;

    public boolean resultOk;
    //2  creer un attribut de type de la classe en question (static)
    public static ServiceUtilisateur instance = null;

    //Singleton => Design Pattern qui permet de creer une seule instance d'un objet 
    //1 rendre le constructeur private
    private ServiceUtilisateur() {
        req = new ConnectionRequest();
    }

    //3 la methode qu'elle va ramplacer le constructeur 
    public static ServiceUtilisateur getinstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }

    //methode d'ajout
    public boolean addAgent(Utilisateur u) {
        String nom = u.getNom();
        String prenom = u.getPrenom();
        String email = u.getEmail();
        String cin = u.getCin();
        String permis = u.getPermis();
        String password = u.getPwd();

        String url = Statics.BASE_URL + "/utilisateur/adduserJSON/new?" + "nom=" + nom + "&prenom=" + prenom + "&email=" + email + "&cin=" + cin + "&permis=" + permis + "&password=" + password ;

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

public void parseAgents(String jsonText, ArrayList<Utilisateur> users) {
    if (jsonText == null) {
        System.err.println("Error: jsonText is null");
        return;
    }

    try {
        JSONParser j = new JSONParser();
        Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        if (!usersListJson.containsKey("root")) {
            System.err.println("Error: JSON data is not in the expected format");
            return;
        }

        java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) usersListJson.get("root");
        for (Map<String, Object> obj : list) {
            System.out.println("obj = " + obj);
            Utilisateur u = new Utilisateur();
            float id = Float.parseFloat(obj.get("iduser").toString());
            u.setIduser((int) id);
            
            u.setCin(obj.get("cin").toString());
            u.setPermis(obj.get("permis").toString());
            u.setNom(obj.get("nom").toString());
            u.setPrenom(obj.get("prenom").toString());
            u.setEmail(obj.get("Email").toString());
         
            users.add(u);
        }

    } catch (IOException ex) {
        System.err.println("Error: " + ex.getMessage());
    }
}



//a
public ArrayList<Utilisateur> getAllAgents() {
    ArrayList<Utilisateur> users = new ArrayList<>();
    String url ="http://127.0.0.1:8000/utilisateur/jsonall";
    try {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(con.getResponseData());
                parseAgents(jsonText, users);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return users;
}

    public boolean deleteAgent(String id ) {
        
        String url = Statics.BASE_URL +"/utilisateur/deleteusersJSON/"+id;
        
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
   public boolean modifierAgent(Utilisateur u,String id) {
        String url = Statics.BASE_URL +"/utilisateur/updateuserJSON/"+id+"?nom=" + u.getNom() + "&permis=" + u.getPermis()+ "&prenom=" + u.getPrenom()+ "&cin=" + u.getCin()+ "&email=" + u.getEmail();
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
