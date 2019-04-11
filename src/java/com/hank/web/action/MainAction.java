package com.hank.web.action;

import com.hank.service.MainService;
import hibernate_pojo.Member;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("mainAction")
@Scope("prototype")
public class MainAction extends GenericAction {

    @Autowired
    @Qualifier("mainService")
    private MainService mainService;

    private Member member;
    private String itemName;
    private String description;
    private int quantity;

    @Action(value = "/main", results = {
        @Result(name = "success", location = "main.jsp")})
    public String execute() {
        return mainService.execute();
    }

    @Action(value = "/addSingleInCart", results = {
        @Result(name = "addSingleInCart", location = "jspf_main/cart.jsp"),
        @Result(name = "input", location = "login.jsp")})
    public String addSingleInCart() {
        // 有要AOP的記得 service.setAction(this) 設置 result = input
        mainService.setAction(this);
        return mainService.addSingleInCart(member, itemName, quantity);
    }

  @Action(value = "/addComboInCart", results = {
        @Result(name = "addComboInCart", location = "jspf_main/cart.jsp"),
        @Result(name = "input", location = "login.jsp")})
    public String addComboInCart() {
        // 有要AOP的記得 service.setAction(this) 設置 result = input
        mainService.setAction(this);
        return mainService.addComboInCart(member, itemName, quantity, description);
    }

    @Action(value = "/logOut", results = {
        @Result(name = "logOut", location = "main.jsp"),
        @Result(name = "input", location = "login.jsp")})
    public String logOut() {
        // 有要AOP的記得 service.setAction(this) 設置 result = input
        mainService.setAction(this);
        return mainService.logOut(member);
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
