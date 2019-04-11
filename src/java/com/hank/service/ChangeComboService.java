package com.hank.service;

import com.hank.domain.cart.Cart;
import com.hank.domain.context.Context;
import com.hank.domain.item.Combo;
import com.hank.domain.item.CondimentDecorator;
import com.hank.domain.item.RealCombo;
import com.hank.domain.menu.Menu;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import hibernate_pojo.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("changeComboService")
public class ChangeComboService extends GenericService{

    public String showSameCategory(RealCombo comboToBeChanged) {
        Map<Combo, ArrayList<Combo>> categories_for_comboToBeChanged = new HashMap<>();
       
        

        //把 comboToBeChanged 的各個內容物 put 進 categories_for_comboToBeChanged 
        this.method1(categories_for_comboToBeChanged, comboToBeChanged);
        
        
        // 從menuItems裡面挖出與categories_for_comboToBeChanged 的 keySet各項目 相同 category 的單品然後塞進 Map 之中
        this.method2(categories_for_comboToBeChanged);

        Map<String, Object> request = this.getRequest();
        request.put("categories_for_comboToBeChanged", categories_for_comboToBeChanged);

        return Action.SUCCESS;

    }
    
    public String changeCombo_submit(RealCombo comboToBeChanged , String new_desc , int quantity){
        Map<String,Object> session = this.getSession();
        Member member = (Member)session.get("member");
        Cart cart = member .getCart();
        cart.alterCombo(comboToBeChanged.getName(), 
                Context.description_fromArrayList_toString(comboToBeChanged.getDescription().toString()),
                new_desc, quantity);
        return "success";
    }

    private Map<String, Object> getRequest()  {
        ActionContext ctx = ActionContext.getContext();
        return (Map<String, Object>) ctx.get("request");
    }
    
    private Map<String, Object> getSession(){
        ActionContext ctx = ActionContext.getContext();
        return ctx.getSession();
    }

    private void method1(Map<Combo, ArrayList<Combo>> categories_for_comboToBeChanged, RealCombo comboToBeChanged) {
        CondimentDecorator desc = comboToBeChanged.getContent();
        for (;;) {
            categories_for_comboToBeChanged.put(desc, new ArrayList<Combo>());
            if (!(desc.getCombo() instanceof CondimentDecorator)) {
                //跑到 comboToBeChanged 的主餐了 // content中的最後一個了
                categories_for_comboToBeChanged.put(desc.getCombo(), new ArrayList<Combo>());
                break;
            }
            desc = (CondimentDecorator) desc.getCombo();
        }
    }
    
    private void method2(Map<Combo, ArrayList<Combo>> categories_for_comboToBeChanged){
        Menu menu = Context.getMenu();
        ArrayList<Combo> menuItems = menu.getMenuItems();
        
        
        Iterator< Map.Entry<Combo, ArrayList<Combo>>> it = categories_for_comboToBeChanged.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Combo, ArrayList<Combo>> entry = it.next();
            String standardCategory = entry.getKey().getCategory();
            for (Combo item : menuItems) {
                if (item.getCategory().equals(standardCategory)) {
                    entry.getValue().add(item);
                }
            }
        }
    }
}
