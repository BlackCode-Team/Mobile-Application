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
import com.mycompany.services.ServicePark;


/**
 *
 * @author Andrew
 */
public class addPark extends Form{
    
    public addPark(Form previous){
        
        setTitle("add park");
        setLayout(BoxLayout.y());
        TextField tfname = new TextField("","Park Name");
        TextField tfville = new TextField("","Park ville");
        TextField nbplace = new TextField("","Park nbspot");
        CheckBox cb = new CheckBox("Status");
       

        Button btnadd =new Button("add park");
        
        
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(tfname.getText().length()==0 && nbplace.getText().length()==0 && tfville.getText().length()==0 ){
                    Dialog.show("Alert","please fill all the fiels","ok",null);
                }
                else{
                    
                    
                    String status ="ferme" ; 
                    if(cb.isSelected())
                        status ="ouvert" ;
                    Park tas = new Park(Integer.parseInt(nbplace.getText()),tfville.getText(),tfname.getText(),status);
                    if(ServicePark.getinstance().addPark(tas)){
                         Dialog.show("Alert","added successfuly","ok",null);
                    }else {
                         Dialog.show("Alert","Err while connecting to server ","ok",null);
                    }
                   
                }
            }
        });
        addAll(tfname,tfville,nbplace,cb,btnadd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev->previous.show());
     
        
    }
    
}
