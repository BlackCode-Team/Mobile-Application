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
import com.mycompany.entities.Badge;
import com.mycompany.services.ServiceBadge;
/**
 *
 * @author MSI
 */
public class addBadge extends Form{
     public addBadge(Form previous){
        
        setTitle("add Badge");
        setLayout(BoxLayout.y());
        TextField typebadge = new TextField("","Type badge");
        TextField nbpoint = new TextField("","nombre de point");
       


        Button btnadd =new Button("add Badge");
        
        
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(typebadge.getText().length()==0 && nbpoint.getText().length()==0 ){
                    Dialog.show("Alert","please fill all the fiels","ok",null);
                }
                else{
                    
                    
                     Badge bad = new Badge(typebadge.getText(),Integer.parseInt(nbpoint.getText()));
                    if(ServiceBadge.getinstance().addBadge(bad)){
                         Dialog.show("Alert","added successfuly","ok",null);
                         new ListBadges(previous).show();
                    }else {
                         Dialog.show("Alert","Err while connecting to server ","ok",null);
                    }
                   
                }
            }
        });
        addAll(typebadge,nbpoint,btnadd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev->previous.show());
     
        
    }
    
}
