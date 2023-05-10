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
import com.mycompany.entities.Utilisateur;
import com.mycompany.services.ServicePark;
import com.mycompany.services.ServiceUtilisateur;

/**
 *
 * @author mhcab
 */
public class ModifierAgent extends Form{
    
    Form f;
    public ModifierAgent(Utilisateur u,Form prev,String id) {
        f=this;
       setTitle(" Modidfier Agent ");
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev->new MenuAgent(prev).show());

        TableLayout tl = new TableLayout(3, 2);
        setLayout(tl);
       
    
       TextField nom = new TextField(u.getNom(), "Nom");
       TextField email = new TextField(u.getEmail(), "Email");
       TextField prenom = new TextField(u.getPrenom(), "Prenom");
       TextField permis = new TextField(u.getPermis(), "Permis");
       TextField cin = new TextField(u.getCin(), "Cin");
      
    
       
       Button btnModifier = new Button("MODIFIER");
        
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(nom.getText().length()==0 && prenom.getText().length()==0 && permis.getText().length()==0 && cin.getText().length()==0 && email.getText().length()==0 ){
                    Dialog.show("Alert","please fill all the fiels","ok",null);
                }
                else{
                    
                    Utilisateur u = new Utilisateur(nom.getText(),prenom.getText(),cin.getText(), email.getText(),permis.getText());
                    if(ServiceUtilisateur.getinstance().modifierAgent(u,id)){
                         Dialog.show("Alert","added successfuly","ok",null);
                    }else {
                         Dialog.show("Alert","Err while connecting to server ","ok",null);
                    }
                   
                }
            }
        });
      
        
         f.addAll(nom,prenom,cin, permis,email,btnModifier);
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
    