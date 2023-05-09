/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

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
import com.mycompany.entities.Park;
import com.mycompany.entities.Vehicule;
import com.mycompany.myapp.Home;
import com.mycompany.services.ServicePark;
import com.mycompany.services.ServiceVehicule;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class ListVehicules extends Form {

    Form current;
   
    private Resources theme;

    public ListVehicules(Resources res, Form previous) {
        current = this; //Back 
     

        setTitle("List Vehicule");
        setLayout(BoxLayout.y());
        
        

        ArrayList<Vehicule> tasks = ServiceVehicule.getinstance().getAllVehicules();
        for (Vehicule p : tasks) {
            addElement(p);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());
    }

    public ListVehicules(Form previous) {
        setTitle("List des Vehicules");
        setLayout(BoxLayout.y());

        ArrayList<Vehicule> tasks = ServiceVehicule.getinstance().getAllVehicules();
        for (Vehicule p : tasks) {
            addElement(p);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());

    }

    public void addElement(Vehicule v) {
        Label a = new Label(v.getModele(), "WelcomeBlue");

        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Label modele = new Label("Modele : " + v.getModele());
        Label matricule = new Label("Matricule : " + v.getMatricule());
        Label type = new Label("Type : " + v.getType());
        Label batterie = new Label("Batterie : " + v.getBatterie());
        Label puissance = new Label("Puissance : " + v.getPuissance());
        Label status = new Label("Status : " + v.getStatus());
        Label prix = new Label("Prix : " + v.getPrix());

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
                if (ServiceVehicule.getinstance().deleteVehicules(String.valueOf(v.getId()))==true) {
                  new ListVehicules(new MenuVehicule( new Home())).show();
                }
            }
        });

        //Update icon 
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
                new ModifierVehicule(v, current,String.valueOf(v.getId())).show();
            }
        });
       cnt.add(matricule);
        cnt.add(type);
        cnt.add(batterie);
        cnt.add(puissance);
        cnt.add(status);
        cnt.add(prix);
        Container BUTTONS = new Container(new BoxLayout(BoxLayout.X_AXIS));

        BUTTONS.addAll(lSupprimer, lModifier);
        cnt.add(BUTTONS);


        add(cnt);


    }

}
