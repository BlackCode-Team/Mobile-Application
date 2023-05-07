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
import com.mycompany.services.ServicePark;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class ListParks extends Form {

    Form current;
   
    private Resources theme;

    public ListParks(Resources res, Form previous) {
        current = this; //Back 
     

        setTitle("List Park");
        setLayout(BoxLayout.y());

        ArrayList<Park> tasks = ServicePark.getinstance().getAllParks();
        for (Park p : tasks) {
            addElement(p);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());
    }

    public ListParks(Form previous) {
        setTitle("List Park");
        setLayout(BoxLayout.y());

        ArrayList<Park> tasks = ServicePark.getinstance().getAllParks();
        for (Park p : tasks) {
            addElement(p);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());

    }

    public void addElement(Park p) {
        Label a = new Label(p.getNom(), "WelcomeBlue");

        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
        Label nom = new Label("Nom  : " + p.getNom());
        Label ville = new Label("Lieu du Park : " + p.getVille());
        Label satut = new Label("Statut : " + p.getStatut());
        Label nb = new Label("Nombre des Spots: " + p.getNbspot());

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
                if (ServicePark.getinstance().deletePark(String.valueOf(p.getIdpark()))) {
                  //  new ListParks(current).show();
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
                new ModifierPark(p, current,String.valueOf(p.getIdpark())).show();
            }
        });
        //button vue sur map 
        Button map = new Button("mapView");
        map.addActionListener(l -> new MapPark(current,String.valueOf(p.getIdpark())).show());
        cnt.add(nom);
        cnt.add(ville);
        cnt.add(satut);
        cnt.add(nb);
        cnt.addAll(lSupprimer, lModifier,map);

        add(cnt);

    }

}
