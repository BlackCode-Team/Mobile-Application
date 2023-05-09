
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
import com.mycompany.entities.Badge;
import com.mycompany.services.ServiceBadge;

/**
 *
 * @author MSI
 */
public class ModifierBadge extends Form{
   Form f;
    public ModifierBadge(Badge ba,Form prev,String id) {
        f=this;
        setTitle(" Modidfier Badge ");
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev->new MenuBadge(prev).show());

        TableLayout tl = new TableLayout(3, 2);
        setLayout(tl);
       
    
       TextField typebadge = new TextField(ba.getTypebadge(), "type de badge");
       TextField nbpoint = new TextField(Integer.toString(ba.getNbpoint()), "nombre de point");
      
       
       Button btnModifier = new Button("MODIFIER");
        
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(typebadge.getText().length()==0 && nbpoint.getText().length()==0 ){
                    Dialog.show("Alert","please fill all the fiels","ok",null);
                }
                else{
                    
                    Badge bad = new Badge(typebadge.getText(),Integer.parseInt(nbpoint.getText()));
                    if(ServiceBadge.getinstance().modifierBadge(bad, id)){
                         Dialog.show("Alert","updated successfuly","ok",null);
                         new ListBadges(f).show();

                      
                    }else {
                         Dialog.show("Alert","Err while connecting to server ","ok",null);
                    }
                   
                }
            }
        });
      
        
         f.addAll(typebadge,nbpoint,btnModifier);
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
