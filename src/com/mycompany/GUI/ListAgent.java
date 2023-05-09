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
import com.mycompany.entities.Utilisateur;
import com.mycompany.services.ServicePark;
import com.mycompany.services.ServiceUtilisateur;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class ListAgent extends Form {

    Form current;
   
    private Resources theme;

    public ListAgent(Resources res, Form previous) {
        current = this; //Back 
     

        setTitle("List Agent");
        setLayout(BoxLayout.y());

        ArrayList<Utilisateur> users = ServiceUtilisateur.getinstance().getAllAgents();
        for (Utilisateur u : users) {
            addElement(u);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());
    }

    public ListAgent(Form previous) {
        setTitle("List Agent");
        setLayout(BoxLayout.y());

        ArrayList<Utilisateur> users = ServiceUtilisateur.getinstance().getAllAgents();
        for (Utilisateur u : users) {
            addElement(u);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());

    }

    public void addElement(Utilisateur u) {
        Label a = new Label(u.getNom(), "Welcome");

        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
        Label nom = new Label("Nom  : " + u.getNom());
        Label prenom = new Label("prenom : " + u.getPrenom());
        Label email = new Label("email : " + u.getEmail());
        Label permis = new Label("permis : " + u.getPermis());
        Label cin = new Label("cin : " + u.getCin());
      

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
                if (ServiceUtilisateur.getinstance().deleteAgent(String.valueOf(u.getIduser()))) {
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
                new ModifierAgent(u, current,String.valueOf(u.getIduser())).show();
            }
        });
        //button vue sur map 
        
        cnt.add(nom);
        cnt.add(prenom);
        cnt.add(email);
        cnt.add(cin);
        cnt.add(permis);
        
        cnt.addAll(lSupprimer, lModifier);

        add(cnt);

    }

}
