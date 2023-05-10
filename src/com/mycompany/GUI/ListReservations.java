/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GUI;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reservation;
import com.mycompany.services.ServiceReservation;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
/**
 *
 * @author ychaa
 */
public class ListReservations extends Form {

    Form current;

    private Resources theme;

    public ListReservations(Resources res, Form previous) {
        current = this; //Back 

        setTitle("List Reservations");
        setLayout(BoxLayout.y());

        ArrayList<Reservation> tasks = ServiceReservation.getinstance().getAllReservations();
        for (Reservation p : tasks) {
            addElement(p);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());
    }

    public ListReservations(Form previous) {
        setTitle("List Reservations");
        setLayout(BoxLayout.y());

        ArrayList<Reservation> tasks = ServiceReservation.getinstance().getAllReservations();
        for (Reservation p : tasks) {
            addElement(p);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());

    }

    public void addElement(Reservation p) {

        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label a = new Label("reservation numero :" + p.getIdreservation());

        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
        Label status = new Label("Statut : " + p.getStatus());
        Label prixres = new Label("Nom  : " + p.getPrixreservation());
        Label datedebut = new Label("Date début : " + p.getDatedebut());
        Label datefin = new Label("Date fin : " + p.getDatefin());
        Label iditineraire = new Label("Itinéraire : " + p.getIditineraire());
        Label idvoiture = new Label("ID voiture : " + p.getIdvehicule());
        Label iduser = new Label("ID utilisateur : " + p.getIduser());

        //supprimer button
        Button lSupprimer = new Button();
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);

        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);

        //click delete icon
        lSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                Dialog dig = new Dialog("Suppression");

                if (dig.show("Suppression", "Vous voulez supprimer cet Reservation ?", "Annuler", "Oui")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }
                //n3ayto l suuprimer men service Reclamation ou nrefreshiw liste 
                if (ServiceReservation.getinstance().deleteReservation(String.valueOf(p.getIdreservation()))) {
                    //  new ListReservations(current).show();
                }
            }
        });

        //    Update icon 
        Button lModifier = new Button();
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);

        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        lModifier.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                //System.out.println("hello update");
                new ModifierReservation(p, current, String.valueOf(p.getIdreservation())).show();
            }
        });
        cnt.add(a);
        cnt.add(prixres);
        cnt.add(datedebut);
        cnt.add(datefin);
       // cnt.add(iditineraire);
      //  cnt.add(iduser);
      //  cnt.add(idvoiture);
        cnt.add(status);

        cnt.addAll(lSupprimer, lModifier);

        add(cnt);

    }

}
