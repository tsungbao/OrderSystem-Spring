package com.hank.service;

import com.hank.dao.MemberDAO;
import com.hank.dao.MerchandiseDAO;
import com.hank.dao.OrderDAO;
import com.hank.domain.cart.Cart;
import com.hank.domain.item.Combo;
import com.hank.domain.item.RealCombo;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import hibernate_pojo.Member;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
public class OrderService extends GenericService{

    public String deleteSingleInCart(Cart cart, String itemName) {
        cart.deleteItem(itemName);
        this.cartItems_cartSinglesMap_cartCombosMap_toRequestScope(cart);
        return "deleteSingleInCart";
    }

    public String deleteComboInCart(Cart cart, String name, String desc) {
        cart.deleteCombo(name, desc);
        this.cartItems_cartSinglesMap_cartCombosMap_toRequestScope(cart);
        return "deleteComboInCart";
    }

    public String deleteAllInCart(Cart cart) {
        cart.deleteAll();
        this.cartItems_cartSinglesMap_cartCombosMap_toRequestScope(cart);
        return "deleteAllInCart";
    }
    
    
    @Transactional(readOnly=false)
    public String submit(Member member) throws RuntimeException{
        try {
            new MemberDAO().submit_member(member, member.getCart().getTotalPrice());
            new MerchandiseDAO().submit_merchandise(member.getCart());
            new OrderDAO().submit_order(member);
        } catch (Exception e) {
            throw new RuntimeException("submit時 資料庫操作發生錯誤");        
        }

        //把cart元素removeAll
        member.getCart().deleteAll();

        return "submit";
    }
    
    public String execute(Cart cart){
        this.cartItems_cartSinglesMap_cartCombosMap_toRequestScope(cart);
        return Action.SUCCESS;
    }
    
    public String changeCombo(RealCombo comboToBeChanged , int quantity){
        ActionContext ctx = ActionContext.getContext();
        Map<String,Object> request = (Map<String,Object>)ctx.get("request");
        request.put("comboToBeChanged", comboToBeChanged);
        request.put("quantity", quantity);
        
        return "changeCombo";
    }

    private void cartItems_cartSinglesMap_cartCombosMap_toRequestScope(Cart cart) {
        ActionContext ctx = ActionContext.getContext();
        Map<String, Object> request = (Map<String, Object>) ctx.get("request");

        //將cart裡面的東西分流成 cartSingle 和 cartReals
        /*cartSinglesMap、cartRealsMap 放到request作用域裡面*/
        Map<Combo, Integer> cartItems = cart.showCartItems();
        Map<Combo, Integer> cartSinglesMap = new HashMap<>();
        Map<RealCombo, Integer> cartRealsMap = new HashMap<>();

        Iterator< Map.Entry<Combo, Integer>> it = cartItems.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Combo, Integer> entry = it.next();
            Combo key = entry.getKey();
            if (key instanceof RealCombo) {
                cartRealsMap.put((RealCombo) key, entry.getValue());
            } else {
                cartSinglesMap.put(key, entry.getValue());
            }
        }

        request.put("cartSinglesMap", cartSinglesMap);
        request.put("cartRealsMap", cartRealsMap);
        /*cartItems 放到request作用域裡面*/
        request.put("cartItems", cartItems);
    }
}
