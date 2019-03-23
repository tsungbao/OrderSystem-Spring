package com.hank.domain.factory;

import com.hank.domain.item.Combo;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleFactory implements ItemFactory {

                                                                     
    public Combo createCombo(int id, int price, String name, String category, int bought) {
        Combo combo = null;
        String itemPackage;
        switch (category) {
            case "主餐":
                itemPackage = "com.hank.domain.item.meal.";
                break;
            case "飲料":
                itemPackage = "com.hank.domain.item.beverage.";
                break;
            case "炸物":
                itemPackage = "com.hank.domain.item.fried.";
                break;
            case "甜點":
                itemPackage = "com.hank.domain.item.dessert.";
                break;
            case "套餐":
                throw new RuntimeException("錯囉！這裡是單點商品不是套餐");
            default:
                throw new RuntimeException("不存在的商品類別");
        }
        try {
            Class clazz = Class.forName(itemPackage + name);
            combo = (Combo) clazz.newInstance();

            combo.setId(id);
            combo.setPrice(price);
            combo.setName(name);
            combo.setCategory(category);
            combo.setBought(bought);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SingleFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return combo;
    }
}
