/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.components.FloatingHint;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.table.TableLayout;
import com.mycompany.entities.Park;
import com.mycompany.myapp.Home;
import com.mycompany.services.ServicePark;

/**
 *
 * @author mhcab
 */
public class ModifierPark extends Form{
    
    Form f;
    public ModifierPark(Park re,Form prev,String id) {
        f=this;
       setTitle(" Modidfier Reservation ");
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev->new MenuPark(prev).show());

        TableLayout tl = new TableLayout(3, 2);
        setLayout(tl);
       
    
       TextField nom = new TextField(re.getNom(), "Nom du PArkt");
       TextField Ville = new TextField(re.getVille(), "Ville du park");
       TextField nbspot = new TextField(Integer.toString(re.getNbspot()), "Nbspot du park");
       TextField statut = new TextField(re.getStatut(), "Statut");
    
       
       Button btnModifier = new Button("MODIFIER");
        
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(nom.getText().length()==0 && nbspot.getText().length()==0 && Ville.getText().length()==0 ){
                    Dialog.show("Alert","please fill all the fiels","ok",null);
                }
                else{
                    
                    Park tas = new Park(Integer.parseInt(nbspot.getText()),Ville.getText(),nom.getText(),statut.getText());
                    if(ServicePark.getinstance().modifierPark(tas,id)){
                         Dialog.show("Alert","added successfuly","ok",null);
                          new ListParks(new MenuPark( new Home())).show();
                    }else {
                         Dialog.show("Alert","Err while connecting to server ","ok",null);
                    }
                   
                }
            }
        });
      
        
         f.addAll(nom,Ville,nbspot,statut,btnModifier);
         f.show();
    }
     /**
     * Shows the specified error message in a modal dialog.
     * @param msg 
     */
    public void showError(String msg) {
        Dialog.show("Failed", msg, "OK", null);
    }  
    }
    