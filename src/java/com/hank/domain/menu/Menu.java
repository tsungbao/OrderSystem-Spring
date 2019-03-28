package com.hank.domain.menu;

import com.hank.dao.MerchandiseDAO;
import com.hank.domain.factory.ComboFactory;
import com.hank.domain.factory.ItemFactory;
import com.hank.domain.factory.SingleFactory;
import com.hank.domain.item.Combo;
import com.hank.domain.item.RealCombo;
import hibernate_pojo.Merchandise;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("menu")
public class Menu {

    private ArrayList<Combo> menuItems = new ArrayList<>();
    private ArrayList<RealCombo> combos = new ArrayList<>();
    private ItemFactory factory;

    @Autowired
    @Qualifier("merchandiseDAO")
    private MerchandiseDAO merchandiseDAO ;
    
    public Menu() {
        this.fillMenuItems();
        this.fillCombos();
    }

    public void fillMenuItems() {
        List<Merchandise> list = merchandiseDAO.getMerchandise_items();
        Iterator<Merchandise> it = list.iterator();
        while (it.hasNext()) {
            Merchandise merchandise = it.next();
            SingleFactory sf = new SingleFactory();

            int id = merchandise.getMerchandiseId();
            int price = merchandise.getPrice();
            String name = merchandise.getMerchandiseName();
            String category = merchandise.getMerchandiseCategory();
            int bought = merchandise.getBought();

            menuItems.add(sf.createCombo(id, price, name, category, bought));
        }
        
    }

    public ArrayList<Combo> getMenuItems() {
        return menuItems;
    }

    public void fillCombos() {

        List<Merchandise> list = merchandiseDAO.getMerchandise_combos();
        Iterator<Merchandise> it = list.iterator();
        while (it.hasNext()) {
            Merchandise realCombo = it.next();

            ComboFactory cf = new ComboFactory();
            

            int id = realCombo.getMerchandiseId();
            String name = realCombo.getMerchandiseName();
            String category = realCombo.getMerchandiseCategory();
            String description = realCombo.getMerchandiseDescription();
            int bought = realCombo.getBought();

            String[] descriptions = description.split(",");

            //將 descriptions 實體化 成 Combo[] contents
            Combo[] contents = new Combo[5];
            this.materializeContent(descriptions, contents);
            
            combos.add(cf.createCombo(id, name, category, description, bought, contents));
        }
    }

    public ArrayList<RealCombo> getCombos() {
        return combos;
    }

    private void materializeContent(String[] descriptions,Combo[] contents) {
        SingleFactory sf = new SingleFactory();
        ArrayList<Combo> contents_ArrayList = new ArrayList<>();
        A1:
        for (String content_String : descriptions) {
            Iterator<Combo> it = this.menuItems.iterator();
            A2:
            while (it.hasNext()) {
                Combo combo = it.next();
                if (combo.getName().equals(content_String)) {
                    // 從 menuItems 中 clone() 出來
                    Combo content = null;
                    try {
                        content = combo.clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    contents_ArrayList.add(content);
                    break A2;
                }
            }
        }
        
        // 將  ArrayList<Combo> contents 轉成 Combo[] contents
        for(int i=0;i < contents_ArrayList.size();i++){
            contents[i] = contents_ArrayList.get(i);
        }
    }
}
