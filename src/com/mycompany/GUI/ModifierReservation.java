
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.components.FloatingHint;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.mycompany.entities.Reservation;
import com.mycompany.services.ServiceReservation;
import java.util.Date;

/**
 *
 * @author mhcab
 */
public class ModifierReservation extends Form {

    Form f;

    public ModifierReservation(Reservation re, Form prev, String id) {
        f = this;
        setTitle(" Modidfier Reservation ");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> new MenuReservation(prev).show());

        TableLayout tl = new TableLayout(3, 2);
        setLayout(tl);

        TextField tfprix = new TextField(Integer.toString((int)re.getPrixreservation()), "Reservation Prix");
        Picker tfdatedeb = new Picker();
        tfdatedeb.setType(Display.PICKER_TYPE_DATE);
        tfdatedeb.setDate(new Date()); // initialiser la date avec la date actuelle
        Picker tfdatefin = new Picker();
        tfdatefin.setType(Display.PICKER_TYPE_DATE);
        tfdatefin.setDate(new Date());
        TextField tfuser = new TextField(Integer.toString(re.getIdreservation()), "user");
        TextField tfitin = new TextField(Integer.toString(re.getIditineraire()), "itineraire");
        TextField tfvehic = new TextField(Integer.toString(re.getIdvehicule()), "vehicule");
        TextField tfstatus = new TextField(re.getStatus(), "status");

        Button btnModifier = new Button("MODIFIER");

        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (tfprix.getText().length() == 0 && tfdatedeb.getText().length() == 0 && tfdatefin.getText().length() == 0 && tfuser.getText().length() == 0 && tfitin.getText().length() == 0 && tfvehic.getText().length() == 0 && tfstatus.getText().length() == 0) {
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
                    if (ServiceReservation.getinstance().modifierReservation(tas, id)) {
                        Dialog.show("Alert", "added successfuly", "ok", null);
                    } else {
                        Dialog.show("Alert", "Err while connecting to server ", "ok", null);
                    }

                }
            }
        });

        f.addAll(tfdatedeb, tfdatefin, tfprix, tfuser, tfitin, tfvehic, tfstatus, btnModifier);

        f.show();
    }

    /**
     * Shows the specified error message in a modal dialog.
     *
     * @param msg
     */
    public void showError(String msg) {
        Dialog.show("Failed", msg, "OK", null);
    }
}
