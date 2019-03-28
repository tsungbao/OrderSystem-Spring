package com.hank.domain.context;

import com.hank.domain.factory.ComboFactory;
import com.hank.domain.factory.SingleFactory;
import com.hank.domain.item.Combo;
import com.hank.domain.item.RealCombo;
import com.hank.domain.menu.Menu;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("context")
public class Context {

    @Autowired
    @Qualifier("menu")
    private static Menu menu;
    private SingleFactory singleFactory;
    private ComboFactory comboFactory;

    public static Menu getMenu() {
        return menu;
    }

    public static String description_fromArrayList_toString(String description) {
        description = description.replace("[", "");
        description = description.replace("]", "");
        description = description.replaceAll(" ", "");
        return description;
    }

    public static void fromString_toList(String desc, ArrayList<String> desc_list) {
        String[] desc_array = desc.split(",");
        for (String content : desc_array) {
            desc_list.add(content);
        }
    }

    public SingleFactory getSingleFactory() {
        singleFactory = new SingleFactory();
        return singleFactory;
    }

    public ComboFactory getComboFactory() {
        comboFactory = new ComboFactory();
        return comboFactory;
    }

    public RealCombo getCombo_FromMenu(String name) {
        boolean getIt = false;
        ArrayList<RealCombo> combos = menu.getCombos();
        RealCombo rightOne = null;
        A1:
        for (RealCombo combo_InMenu : combos) {
            if (combo_InMenu.getName().equals(name)) {
                getIt = true;
                rightOne = combo_InMenu;
                break A1;
            }
        }
        if (getIt == false) {
            throw new RuntimeException("menu裡面找不到這個套餐名稱");
        }
        return rightOne;
    }

    public Combo getSingle_FromMenu(String name) {
        boolean getIt = false;
        ArrayList<Combo> items = menu.getMenuItems();
        Combo rightOne = null;
        A1:
        for (Combo item_InMenu : items) {
            if (item_InMenu.getName().equals(name)) {
                getIt = true;
                rightOne = item_InMenu;
                break A1;
            }
        }
        if (getIt == false) {
            throw new RuntimeException("menu裡面找不到這個單品名稱");
        }
        return rightOne;
    }
}
