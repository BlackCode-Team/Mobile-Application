/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Reservation;
import com.mycompany.services.ServiceReservation;
import java.util.Date;

/**
 *
 * @author Andrew
 */
public class addReservation extends Form {

    public addReservation(Form previous) {

        setTitle("add reservation");
        setLayout(BoxLayout.y());
        TextField tfprix = new TextField("", "Reservation Prix");
        Picker tfdatedeb = new Picker();
        tfdatedeb.setType(Display.PICKER_TYPE_DATE);
        tfdatedeb.setDate(new Date()); // initialiser la date avec la date actuelle
        Picker tfdatefin = new Picker();
        tfdatefin.setType(Display.PICKER_TYPE_DATE);
        tfdatefin.setDate(new Date());
        TextField tfuser = new TextField("", "user");
        TextField tfitin = new TextField("", "itineraire");
        TextField tfvehic = new TextField("", "vehicule");
        TextField tfstatus = new TextField("", "status");

        Button btnadd = new Button("add reservation");

        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (tfprix.getText().length() == 0) {
                    Dialog.show("Alert", "please fill all the fiels", "ok", null);
                } else {
                    float prix = Float.parseFloat(tfprix.getText());
                    Date datedeb = tfdatedeb.getDate();
                    Date datefin = tfdatefin.getDate();
                    int user = Integer.parseInt(tfuser.getText());
                    int itineraire = Integer.parseInt(tfitin.getText());
                    int vehicule = Integer.parseInt(tfvehic.getText());
                    String status = tfstatus.getText();

                    Reservation tas = new Reservation(datedeb, datefin, vehicule, user, itineraire, status, prix);
                    if (ServiceReservation.getinstance().addReservation(tas)) {
                        Dialog.show("Alert", "added successfuly", "ok", null);
                    } else {
                        Dialog.show("Alert", "Err while connecting to server ", "ok", null);
                    }

                }
            }
        });
        addAll(tfdatedeb,tfdatefin,tfprix,tfuser,tfitin,tfvehic,tfstatus, btnadd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());

    }

}
