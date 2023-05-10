/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

/**
 *
 * @author ychaa
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Reservation;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Andrew
 */
public class ServiceReservation {

    ArrayList<Reservation> reservations;
    ConnectionRequest req;

    public boolean resultOk;
    //2  creer un attribut de type de la classe en question (static)
    public static ServiceReservation instance = null;

    //Singleton => Design Pattern qui permet de creer une seule instance d'un objet 
    //1 rendre le constructeur private
    private ServiceReservation() {
        req = new ConnectionRequest();
    }

    //3 la methode qu'elle va ramplacer le constructeur 
    public static ServiceReservation getinstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }

    //methode d'ajout
    public boolean addReservation(Reservation t) {
        Date datedebut = t.getDatedebut();
        Date datefin = t.getDatefin();
        int idvehicule = t.getIdvehicule();
        int iduser = t.getIduser();
        int iditineraire = t.getIditineraire();
        float prixreservation = t.getPrixreservation();
        String status = t.getStatus();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedStartDate = sdf.format(datedebut);
        String formattedEndDate = sdf.format(datefin);

        String url = Statics.BASE_URL + "/reservation/newres/JSON/?" + "datedebut=" + formattedStartDate + "&datefin=" + formattedEndDate + "&idvehicule=" + idvehicule + "&idutilisateur=" + iduser + "&iditineraire=" + iditineraire + "&status=" + status + "&prixreservation=" + prixreservation;

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

    public void parseReservations(String jsonText, ArrayList<Reservation> reservations) {
        if (jsonText == null) {
            System.err.println("Error: jsonText is null");
            return;
        }

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> reservationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            if (!reservationsListJson.containsKey("root")) {
                System.err.println("Error: JSON data is not in the expected format");
                return;
            }

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) reservationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                System.out.println("obj = " + obj);
                Reservation t = new Reservation();
                float id = Float.parseFloat(obj.get("idreservation").toString());
                t.setIdreservation((int) id);

                float prix = Float.parseFloat(obj.get("prixreservation").toString());
                t.setPrixreservation(prix);
//                float idveh = Float.parseFloat(obj.get("idvehicule").toString());
//                t.setIdvehicule((int) idveh);

//                float iduser = Float.parseFloat(obj.get("utilisateur").toString());
//                t.setIduser((int) iduser); 
//                float iditin = Float.parseFloat(obj.get("itineraire").toString());
//                t.setIditineraire((int) iditin); 
                t.setStatus(obj.get("status").toString());
                try {
                    final String dateString = obj.get("datedebut").toString();
                    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    final Date dateDebut = format.parse(dateString);
                    t.setDatedebut(dateDebut);
                } catch (ParseException ex) {
                    System.err.println("Error parsing date: " + ex.getMessage());
                }
                try {
                    final String dateString = obj.get("datefin").toString();
                    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    final Date dateFin = format.parse(dateString);
                    t.setDatefin(dateFin);
                } catch (ParseException ex) {
                    System.err.println("Error parsing date: " + ex.getMessage());
                }

                reservations.add(t);
            }

        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

//a
    public ArrayList<Reservation> getAllReservations() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        String url = "http://127.0.0.1:8000/reservation/jsonall/a";
        try {
            ConnectionRequest con = new ConnectionRequest();
            con.setUrl(url);
            con.setPost(false);
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    String jsonText = new String(con.getResponseData());
                    parseReservations(jsonText, reservations);

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(con);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return reservations;
    }

    public boolean deleteReservation(String id) {

        String url = Statics.BASE_URL + "/reservation/deleteJSON/" + id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public boolean modifierReservation(Reservation t, String id) {
        Date datedebut = t.getDatedebut();
        Date datefin = t.getDatefin();
        int idvehicule = t.getIdvehicule();
        int iduser = t.getIduser();
        int iditineraire = t.getIditineraire();
        float prixreservation = t.getPrixreservation();
        String status = t.getStatus();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedStartDate = sdf.format(datedebut);
        String formattedEndDate = sdf.format(datefin);

        String url = Statics.BASE_URL + "/reservation/JSON/edit/" + id + "?datedebut=" + formattedStartDate + "&datefin=" + formattedEndDate + "&idvehicule=" + idvehicule + "&idutilisateur=" + iduser + "&iditineraire=" + iditineraire + "&status=" + status + "&prixreservation=" + prixreservation;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }
}
