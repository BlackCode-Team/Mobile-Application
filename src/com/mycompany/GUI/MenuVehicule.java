package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.FontImage;

public class MenuVehicule extends Form {
    private Form prev;

    public MenuVehicule(Form prev) {
        this.prev = prev;
        setTitle("Gestion des véhicules");

        // Ajouter un label pour indiquer à l'utilisateur de choisir une option
        Label label = new Label("Choisir une option :");
        addComponent(label);

        // Créer des boutons pour les différentes options
        Button ajouterButton = new Button("Ajouter un véhicule");
        Button listerButton = new Button("Lister les véhicules");
        Button chercherButton = new Button("Chercher un véhicule");

        // Ajouter des listeners aux boutons pour répondre aux clics
        ajouterButton.addActionListener(e -> new addVehicule(this).show());
        listerButton.addActionListener(e -> new ListVehicules(this).show());
        //chercherButton.addActionListener(e -> new ChercherVehiculeForm(this).show());

        // Ajouter les boutons à un container
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.addComponent(ajouterButton);
        container.addComponent(listerButton);
        container.addComponent(chercherButton);
        addComponent(container);

        // Ajouter un bouton "Retour" dans la toolbar pour retourner à l'écran précédent
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
    }
}
