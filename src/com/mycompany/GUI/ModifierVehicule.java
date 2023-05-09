/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.table.TableLayout;
import com.mycompany.entities.Vehicule;
import com.mycompany.myapp.Home;
import com.mycompany.services.ServiceVehicule;

/**
 *
 * @author Rania2
 */
public class ModifierVehicule extends Form {

    Form f;
    
    public ModifierVehicule(Vehicule v, Form prev, String id) {
        f = this;
        setTitle("Modifier Véhicule");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> new MenuVehicule(prev).show());

        TableLayout tl = new TableLayout(3, 2);
        setLayout(tl);

        TextField type = new TextField(v.getType(), "Type du véhicule");
        TextField modele = new TextField(v.getModele(), "Modèle");
        TextField batterie = new TextField(Integer.toString(v.getBatterie()), "Batterie");
        TextField status = new TextField(v.getStatus(), "Statut");
        TextField prix = new TextField(Integer.toString(v.getPrix()), "Prix");
        TextField matricule = new TextField(v.getMatricule(), "Matricule");
        TextField puissance = new TextField(Integer.toString(v.getPuissance()), "Puissance");

        Button btnModifier = new Button("MODIFIER");

        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (type.getText().length() == 0 || batterie.getText().length() == 0 || modele.getText().length() == 0
                        ||status.getText().length() == 0
                        || prix.getText().length() == 0 || matricule.getText().length() == 0
                        || puissance.getText().length() == 0 ) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    Vehicule veh = new Vehicule(type.getText(), modele.getText(), Integer.parseInt(batterie.getText()),
                            status.getText(), Integer.parseInt(prix.getText()), matricule.getText(),
                            Integer.parseInt(puissance.getText()));

                    veh.setId(v.getId()); // Important : set the id of the updated vehicle to the original id
                    if (ServiceVehicule.getinstance().modifierVehicule(veh, id)) {
                        Dialog.show("Alert", "Vehicle updated successfully", "OK", null);
                         new ListVehicules(new MenuVehicule( new Home())).show();
                    } else {
                        Dialog.show("Alert", "Error while connecting to server ", "OK", null);
                    }

                }
            }
        });

        f.addAll(type, batterie, modele, status, prix, matricule, puissance, btnModifier);
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

