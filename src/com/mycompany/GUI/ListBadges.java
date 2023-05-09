package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Badge;
import com.mycompany.services.ServiceBadge;
import com.mycompany.services.ServicePark;
import java.util.ArrayList;


public class ListBadges extends Form {
    
     Form current;
   
    private Resources theme;
    
    public ListBadges(Resources res, Form previous) {
        current = this; //Back 
     

        setTitle("List Badge");
        setLayout(BoxLayout.y());

        ArrayList<Badge> badges = ServiceBadge.getinstance().getAllBadges();
        for (Badge p : badges) {
            addElement(p);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());

    }
        
    public ListBadges(Form previous) {
        setTitle("List Badge");
        setLayout(BoxLayout.y());

        ArrayList<Badge> tasks = ServiceBadge.getinstance().getAllBadges();
        for (Badge b : tasks) {
            addElement(b);
        }


        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.show());


    }
        public void addElement(Badge b) {
        Label a = new Label(b.getTypebadge(), "Welcome");

        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
        Label typebadge = new Label("Type de badge : " + b.getTypebadge());
        Label nbpoint = new Label("nombre de point : " + b.getNbpoint());


        //supprimer button
        Button lSupprimer = new Button();
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);

        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);

        //click delete icon
        lSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                Dialog dig = new Dialog("Suppression");

               
                //n3ayto l suuprimer men service Reclamation ou nrefreshiw liste 
                if (ServiceBadge.getinstance().deleteBadge(String.valueOf(b.getId()))) {
                  new ListBadges(current).show();
                }
            }
        });

        //Update icon 
        Button lModifier = new Button();
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);

        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        lModifier.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                //System.out.println("hello update");
                new ModifierBadge(b, current,String.valueOf(b.getId())).show();
            }
        });
        //button vue sur map 
        
        cnt.add(typebadge);
        cnt.add(nbpoint);
        cnt.addAll(lSupprimer, lModifier);

        add(cnt);

    }
    
    
    
    
    
    
    
    
    
    
    
}
