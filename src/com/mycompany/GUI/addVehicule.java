/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Vehicule;
import com.mycompany.myapp.Home;
import com.mycompany.services.ServiceVehicule;

public class addVehicule extends Form {    
    public addVehicule(Form previous){
        
        setTitle("Add Vehicle");
        setLayout(BoxLayout.y());
        TextField tfMatricule = new TextField("","Matricule");
        TextField tfModele = new TextField("","Modele");
        TextField tfPrix = new TextField("","Prix");
        TextField tfPuissance = new TextField("","Puissance");
        TextField tfImg = new TextField("","Image URL");
        ComboBox<String> cbType = new ComboBox<>("Voiture", "Trottinette");
        CheckBox cbStatus = new CheckBox("Status");

        Button btnAdd =new Button("Add Vehicle");
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(tfMatricule.getText().length()==0 || tfModele.getText().length()==0 || tfPrix.getText().length()==0 || tfPuissance.getText().length()==0 || tfImg.getText().length()==0 ){
                    Dialog.show("Alert","Please fill all the fields","OK",null);
                }
                else{
                    
                    String status ="non disponible" ; 
                    if(cbStatus.isSelected())
                        status ="disponible" ;
                        
                    Vehicule vehicule = new Vehicule();
                    vehicule.setMatricule(tfMatricule.getText());
                    vehicule.setModele(tfModele.getText());
                    vehicule.setPrix(Integer.parseInt(tfPrix.getText()));
                    vehicule.setPuissance(Integer.parseInt(tfPuissance.getText()));
                    vehicule.setImg(tfImg.getText());
                    vehicule.setType(cbType.getSelectedItem());
                    vehicule.setStatus(status);
                    vehicule.setBatterie(100);
    
                    vehicule.setBatterie(100);
                    if(ServiceVehicule.getinstance().addVehicule(vehicule)){
                         Dialog.show("Alert","Vehicle added successfully","OK",null);
                          new ListVehicules(new MenuVehicule(new Home())).show();
                    }else {
                         Dialog.show("Alert","Error while connecting to server ","OK",null);
                    }
                   
                }
            }
        });
        addAll(tfMatricule,tfModele,tfPrix,tfPuissance,tfImg,cbType,cbStatus,btnAdd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev->previous.show());
     
        
    }
    
}
