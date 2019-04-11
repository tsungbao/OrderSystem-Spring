package com.hank.domain.cart;

import com.hank.domain.factory.ComboFactory;
import com.hank.domain.item.Combo;
import com.hank.domain.item.RealCombo;
import com.hank.domain.context.Context;
import com.hank.domain.menu.Menu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("cart")
@Scope("prototype")
public class Cart implements Subject,Serializable {
    
    private static final long serialVersionUID=1 ;
    private Map<Combo, Integer> cartItems = new HashMap<>();
    private ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        Iterator it = observers.iterator();
        while (it.hasNext()) {
            Observer ob = (Observer) it.next();
            ob.update(cartItems);
        }
    }

    public Map<Combo, Integer> showCartItems() {
        return cartItems;
    }

    public void addCart_Item(String name, int quantity) {
        Set<Combo> set = cartItems.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Combo cartItem = (Combo) it.next();
            if (cartItem.getName().equals(name)) {
                // 原本的cartItems裡面就已經有該商品了
                int cartItemCurrent_Quantity = (int) cartItems.get(cartItem);
                cartItems.remove(cartItem);
                cartItems.put(cartItem, quantity + cartItemCurrent_Quantity);
                this.notifyObserver();
                return;
            }
        }

        //原本cartItems裡面並沒有該商品
        Menu menu = Context.getMenu();
        ArrayList<Combo> menuItems = menu.getMenuItems();

        // 從menu單品找
        Iterator<Combo> itt = menuItems.iterator();
        while (itt.hasNext()) {
            Combo menuItem = itt.next();
            if (menuItem.getName().equals(name)) {
                Combo newItem = null;
                try {
                    //準備clone出來
                    newItem = menuItem.clone();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
                }
                cartItems.put(newItem, quantity);
                this.notifyObserver();
                return;
            }
        }

    }

    // 從menu套餐找 找到同一個名稱的套餐先把它clone出來成 newItem
    // 看看這個套餐描述跟從 menu clone出來的 一不一樣
    // 不一樣就要重新create一個 newItem
    // 再把cartItems 裡面的 combo 一個一個找出來 比對套餐內容， 一樣疊加，不一樣另增
    public void addCart_Combo(String name, String description, int quantity) {
        Menu menu = Context.getMenu();

        ArrayList<RealCombo> menu_Combos = menu.getCombos();
        Iterator<RealCombo> it = menu_Combos.iterator();
        RealCombo newItem = null;

        // 從menu套餐找 找到同一個名稱的套餐先把它clone出來
        while (it.hasNext()) {
            RealCombo comboInMenu = (RealCombo) it.next();
            if (comboInMenu.getName().equals(name)) {
                try {
                    newItem = (RealCombo) comboInMenu.clone();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // 看看這個套餐描述跟原本一不一樣
        boolean noramlCombo = newItem.getDescription().equals(description);

        // 不一樣就要重新create一個
        if (!noramlCombo) {
            newItem = this.changeContentCombo(newItem, description);
        }

        // 把cartItems 裡面的東西全部抓出來 找出有沒有套餐名稱且套餐描述皆相符合的cartItem
        Set<Combo> set = cartItems.keySet();
        Iterator<Combo> it3 = set.iterator();

        while (it3.hasNext()) {
            Combo cartItem = it3.next();
            A1:
            if (cartItem instanceof RealCombo) {
                //先比對名字
                if (!(cartItem.getName().equals(name))) {
                    break A1;
                }

                // 在比對套餐描述
                String[] descriptions = description.split(",");
                ArrayList<String> cartItemDescription = ((RealCombo) cartItem).getDescription();
                List<String> descriptionss = Arrays.asList(descriptions);
                for (int i = 0; i < descriptionss.size(); i++) {
                    if (!(cartItemDescription.get(i).equals(descriptionss.get(i)))) {
                        break A1;
                    }
                }

                int originalNumber = cartItems.get(cartItem);
                quantity += originalNumber;
                cartItems.put(cartItem, quantity);
                this.notifyObserver();
                return;
            }
        }

        //  cartItems 裡面 沒有 名稱且套餐描述皆相符合的cartItem
        cartItems.put(newItem, quantity);
        this.notifyObserver();
        return;
    }

    public void deleteItem(String name) {
        //  找看看購物籃有沒有同樣名字的單品
        //  找到刪除
        //  找不到報錯
        Set<Combo> set = cartItems.keySet();
        for (Combo cartItem : set) {
            if (cartItem.getName().equals(name)) {
                //  找到刪除
                cartItems.remove(cartItem);
                this.notifyObserver();
                return;
            }
        }
        //  找不到報錯
        throw new RuntimeException("理論上是不會刪除購物籃中找不到的商品");
    }
                                          //"雪碧,八塊雞塊"
    public void deleteCombo(String name, String description) {
        Set<Combo> itemsInCart = cartItems.keySet();
        Iterator<Combo> it = itemsInCart.iterator();
        while (it.hasNext()) {
            Combo itemInCart = it.next();
            String itemInCart_Name = itemInCart.getName();
            if (itemInCart_Name.equals(name)) {
                //找到名字相同的了
                RealCombo itemInCart_cd;
                try {
                    itemInCart_cd = (RealCombo) itemInCart;
                } catch (Exception e) {
                    throw new RuntimeException("理論上一定可以轉成RealCombo喔！");
                }
                // arrayList 轉成 string
                ArrayList<String> itemInCart_Description = itemInCart_cd.getDescription();
                StringBuffer d = new StringBuffer();
                for (String dd : itemInCart_Description) {
                    if (itemInCart_Description.indexOf(dd) == itemInCart_Description.size() - 1) {
                        d.append(dd);
                    } else {
                        d.append(dd);
                        d.append(",");
                    }
                }

                String desc = d.toString();
                //

                //再確認descrpition有沒有相同
                if (description.equals(desc)) {
                    cartItems.remove(itemInCart);
                    this.notifyObserver();
                    return;
                }

            }
        }
        throw new RuntimeException("理論上是不會刪除購物籃中找不到的商品");
    }

    public void deleteAll() {
        cartItems.clear();
        this.notifyObserver();
    }
                                                //"雪碧,八塊雞塊"       //"可樂,八塊雞塊"
    public void alterCombo(String comboName, String original_Description, String new_Description, int quantity) {
        deleteCombo(comboName, original_Description);
        addCart_Combo(comboName, new_Description, quantity);
        this.notifyObserver();
    }

    public int getQuantity(){
        int quantity =0 ;
        Collection <Integer> quantities= cartItems.values();
        for(int q:quantities){
            quantity+=q;
        }
        return quantity;
    }
     
    public int getTotalPrice(){
        int totalPrice =0;
        Set<Combo> set = cartItems.keySet();
        for(Combo item:set){
            totalPrice+=item.getPrice()*cartItems.get(item);
        }
        return totalPrice;
    }  
                                                            //"可樂,八塊雞塊"
    private RealCombo changeContentCombo(RealCombo newItem, String description) {
        Menu menu = Context.getMenu();

        ComboFactory comboFactory = (new Context()).getComboFactory();
        String comboName = newItem.getName();
        int id = newItem.getId();
        int bought = newItem.getBought();
        String category = newItem.getCategory();

        // 利用 description 創建 contents            
        Combo[] contents = null;
        String[] descriptions = description.split(",");

        int i = descriptions.length;
        contents = new Combo[i];
        i = 0;
        A1:
        for (String content : descriptions) {

            ArrayList<Combo> menu_Items = menu.getMenuItems();

            for (Combo item : menu_Items) {
                try {
                    if (item.getName().equals(content)) {
                        contents[i] = item.clone();
                        i++;
                        continue A1;
                    }
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            throw new RuntimeException("此商品停賣囉！");

        }

        newItem = comboFactory.createCombo(id, comboName, category, description, bought, contents);
        return newItem;
    }
    
}
