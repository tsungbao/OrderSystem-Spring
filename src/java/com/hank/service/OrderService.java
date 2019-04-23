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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
public class OrderService extends GenericService {

    public String deleteSingleInCart(String itemName) {
        Cart cart = this.getCartFromMember();
        cart.deleteItem(itemName);
        this.cartItems_cartSinglesMap_cartCombosMap_toRequestScope(cart);
        return "deleteSingleInCart";
    }

    public String deleteComboInCart(String name, String desc) {
        Cart cart = this.getCartFromMember();
        cart.deleteCombo(name, desc);
        this.cartItems_cartSinglesMap_cartCombosMap_toRequestScope(cart);
        return "deleteComboInCart";
    }

    public String deleteAllInCart() {
        Cart cart = this.getCartFromMember();
        cart.deleteAll();
        this.cartItems_cartSinglesMap_cartCombosMap_toRequestScope(cart);
        return "deleteAllInCart";
    }

    @Autowired
    @Qualifier("memberDAO")
    private MemberDAO memberDAO;
    @Autowired
    @Qualifier("orderDAO")
    private OrderDAO orderDAO;
    @Autowired
    @Qualifier("merchandiseDAO")
    private MerchandiseDAO merchandiseDAO;

    @Transactional
    public String submit() throws RuntimeException {
        Member member = this.getMemberFromSession();
        try {
            memberDAO.submit_member(member, member.getCart().getTotalPrice());
            merchandiseDAO.submit_merchandise(member.getCart());
            orderDAO.submit_order(member);
        } catch (Exception e) {
            throw new RuntimeException("submit時 資料庫操作發生錯誤");
        }

        //把cart元素removeAll
        member.getCart().deleteAll();

        return "submit";
    }

    public String execute() {
        Cart cart = this.getCartFromMember();
        this.cartItems_cartSinglesMap_cartCombosMap_toRequestScope(cart);
        return Action.SUCCESS;
    }

    public String changeCombo(int cartId_comboToBeChanged, int quantity) {
        ActionContext ctx = ActionContext.getContext();
        
        Map<String, Object> session = (Map<String, Object>) ctx.getSession();
        Cart cart = ((Member)session.get("member")).getCart();
        RealCombo comboToBeChanged = null;
        try{
            comboToBeChanged = (RealCombo)cart.getItemFromCartId(cartId_comboToBeChanged);
        }catch(NullPointerException e){
            throw new RuntimeException("cart中找不到cartId為cartId_comboToBeChanged的物品");
        }    
        
        Map<String, Object> request = (Map<String, Object>) ctx.get("request");
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
    
    private Cart getCartFromMember(){
        Member member = this.getMemberFromSession();
        return member.getCart();
    }
}
