/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.mycompany.GUI.*;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Andrew
 */
public class Home extends Form {

    Form current;
    private Resources theme;

    public Home() {
        current = this; //Back 

        setTitle("Welcome TO CARNGO");
        setLayout(BoxLayout.y());
        Button park = new Button("Gestion des Parks");
        Button badge = new Button("Gestion des badges");
        Button vehicule = new Button("Gestion des véhicules");
        Button reservation = new Button("Gestion des reservations");
        Button agent = new Button("Gestion des Agents");


        park.addActionListener((evt) -> new MenuPark(current).show());
        vehicule.addActionListener((evt) -> new MenuVehicule(current).show());
        badge.addActionListener((evt) -> new MenuBadge(current).show());
        reservation.addActionListener((evt) -> new MenuReservation(current).show());
        agent.addActionListener((evt) -> new MenuAgent(current).show());


        addAll(park);
        addAll(vehicule);  
        addAll(badge);
        addAll(reservation);
        addAll(agent);
    }

}
