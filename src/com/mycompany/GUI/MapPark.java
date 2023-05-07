/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Park;
import com.mycompany.services.ServicePark;
import com.mycompany.utils.Statics;

/**
 *
 * @author mhcab
 */
public class MapPark extends Form {

public MapPark(Form prev,String id) {
    super(new BorderLayout());
    BrowserComponent browser = new BrowserComponent();
    String url = Statics.BASE_URL +"/park/jsonmap/"+id;
    browser.setURL(url);
    add(BorderLayout.CENTER, browser);

    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> new MenuPark(prev).show());
}

}
