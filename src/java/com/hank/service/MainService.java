package com.hank.service;

import com.hank.domain.cart.Cart;
import com.hank.domain.context.Context;
import com.hank.domain.item.Combo;
import com.hank.domain.item.RealCombo;
import com.hank.domain.menu.Menu;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import hibernate_pojo.Member;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("mainService")
public class MainService extends GenericService {
    public String execute(){
        Menu menu = Context.getMenu();
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        ArrayList<Combo> menuItems = menu.getMenuItems();
        ArrayList<RealCombo> combos = menu.getCombos();
        request.put("menuItems", menuItems);
        request.put("combos", combos);
        return Action.SUCCESS;
    }
    
    public String addSingleInCart(String itemName,int quantity){
        Member member = this.getMemberFromSession();
        Cart cart = member.getCart();
        if(quantity==0){
            //還沒輸入數量
        }else{
             cart.addCart_Item(itemName, quantity);      
        }
        return "addSingleInCart";
    }
    
    public String addComboInCart(String itemName,int quantity,String description){
        Member member = this.getMemberFromSession();
        Cart cart = member.getCart();
        if(quantity==0){
            //還沒輸入數量
        }else{
             cart.addCart_Combo(itemName, description, quantity);
        }
        return "addComboInCart";
    }
    
    public String logOut(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.remove("member");
        this.execute();
        return "logOut";
    }
    
}
