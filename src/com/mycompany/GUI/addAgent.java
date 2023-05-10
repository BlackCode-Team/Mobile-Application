/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Park;
import com.mycompany.entities.Utilisateur;
import com.mycompany.myapp.Home;
import com.mycompany.services.ServicePark;
import com.mycompany.services.ServiceUtilisateur;


/**
 *
 * @author Andrew
 */
public class addAgent extends Form{
    
    public addAgent(Form previous){
        
        setTitle("add agent");
        setLayout(BoxLayout.y());
        TextField nomAgent = new TextField("","Agent Name");
        TextField prenomAgent = new TextField("","Prenom");
        TextField emailAgent = new TextField("","Email");
        TextField cinAgent = new TextField("","cin");
        TextField permisAgent = new TextField("","permis");
        

        Button btnadd =new Button("add agent");
        
        
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(nomAgent.getText().length()==0 && prenomAgent.getText().length()==0 && permisAgent.getText().length()==0 && cinAgent.getText().length()==0 && emailAgent.getText().length()==0 ){
                    Dialog.show("Alert","please fill all the fiels","ok",null);
                }
                else{
                    
                    
                    
                                                //   (String nom, String prenom, String cin, String permis, String email)
                    Utilisateur u = new Utilisateur(nomAgent.getText(),prenomAgent.getText(),cinAgent.getText(),permisAgent.getText(),emailAgent.getText());
                    if(ServiceUtilisateur.getinstance().addAgent(u)){
                         Dialog.show("Alert","added successfuly","ok",null);
                      new ListAgent(new MenuPark( new Home())).show();
                    }else {
                         Dialog.show("Alert","Err while connecting to server ","ok",null);
                    }
                   
                }
            }
        });
        addAll(nomAgent,prenomAgent,cinAgent,permisAgent,emailAgent,btnadd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev->previous.show());
     
        
    }
    
}
