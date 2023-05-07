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

    public Home(Form previous) {
        current = this; //Back 

        setTitle("Welcome TO CARNGO");
        setLayout(BoxLayout.y());
        Button park = new Button("Gestion des Parks");

        park.addActionListener((evt) -> new MenuPark(current).show());

        addAll(park);

    }

}