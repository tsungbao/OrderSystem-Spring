
package com.hank.web.action;

import com.hank.domain.cart.Cart;
import com.hank.domain.item.RealCombo;
import com.hank.service.OrderService;
import hibernate_pojo.Member;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends GenericAction {
    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;
    
    private int comboToBeChangedQuantity;
    private Member member;
    private String name;
    private String description;
    private RealCombo comboToBeChanged;
    
    @Action(value = "/order", results = {
        @Result(name = "success", location = "order.jsp"),
        @Result(name = "input", location = "login.jsp")})
    public String execute(){
        // 有要AOP的記得 service.setAction(this)  設置 result = input
        Cart cart = member.getCart();
        orderService.setAction(this);
        return orderService.execute(cart);
    }
    
    
    @Action(value = "/deleteAllInCart", results = {
        @Result(name = "deleteAllInCart", location = "order.jsp"),
        @Result(name = "input", location = "login.jsp")})
    public String deleteAllInCart(){
        // 有要AOP的記得 service.setAction(this)  設置 result = input
        Cart cart = member.getCart();
        orderService.setAction(this);
        return orderService.deleteAllInCart(cart);
    }
    
    
    @Action(value = "/deleteSingleInCart", results = {
        @Result(name = "deleteAllInCart", location = "order.jsp"),
        @Result(name = "input", location = "login.jsp")})
    public String deleteSingleInCart(){
        // 有要AOP的記得 service.setAction(this)  設置 result = input
        Cart cart = member.getCart();
        orderService.setAction(this);
        return orderService.deleteSingleInCart(cart, name);
    }
    
    
    @Action(value = "/deleteComboInCart", results = {
        @Result(name = "deleteAllInCart", location = "order.jsp"),
        @Result(name = "input", location = "login.jsp")})
    public String deleteComboInCart(){
        // 有要AOP的記得 service.setAction(this)  設置 result = input
        Cart cart = member.getCart();
        orderService.setAction(this);
        return orderService.deleteComboInCart(cart, name, description);
    }
    
    @Action(value = "/submit", results = {
        @Result(name = "submit", location = "main.jsp"),
        @Result(name = "input", location = "login.jsp"),
        @Result(name = "fail" , location="order.jsp")})
    public String submit(){
        // 有要AOP的記得 service.setAction(this)  設置 result = input
        orderService.setAction(this);
        String result="";
        try{
            result = orderService.submit(member);
        }catch(Exception e){
            result = "fail";
            this.addActionError(e.getMessage());
        }
        return result;
    }
    
     @Action(value = "/changeCombo_order", results = {
        @Result(name = "changeCombo", location = "/changeCombo.action" , type="chain"),
        @Result(name = "input", location = "login.jsp")})
    public String changeCombo(){
        // 有要AOP的記得 service.setAction(this)  設置 result = input
        orderService.setAction(this);
        return orderService.changeCombo(comboToBeChanged,this.comboToBeChangedQuantity);
    }
    
    
    
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RealCombo getComboToBeChanged() {
        return comboToBeChanged;
    }

    public void setComboToBeChanged(RealCombo comboToBeChanged) {
        this.comboToBeChanged = comboToBeChanged;
    }   
    
    public int getComboToBeChangedQuantity() {
        return comboToBeChangedQuantity;
    }

    public void setComboToBeChangedQuantity(int comboToBeChangedQuantity) {
        this.comboToBeChangedQuantity = comboToBeChangedQuantity;
    }
}
