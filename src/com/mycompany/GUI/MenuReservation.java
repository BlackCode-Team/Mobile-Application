
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Home;

/**
 *
 * @author Andrew
 */
public class MenuReservation extends Form {
     Form current;
private Resources theme;


    public MenuReservation(Form prev){
   current = this;
    setTitle("Gestion des reservations");
    setLayout(new BorderLayout());
    add(BorderLayout.NORTH, new Label("choose an option "));
    
    Container buttonsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Button b1 = new Button("Ajouter Reservation");
    Button b2 = new Button("List des Reservations");

    b1.addActionListener(l -> new addReservation(current).show());
    b2.addActionListener(l -> new ListReservations(current).show());
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, e -> new Home().showBack());
    
    buttonsContainer.add(b1);
    buttonsContainer.add(b2);
    
    add(BorderLayout.CENTER, buttonsContainer);
         
    }
    
}
