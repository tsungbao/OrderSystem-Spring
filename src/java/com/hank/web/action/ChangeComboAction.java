package com.hank.web.action;

import com.hank.domain.item.RealCombo;
import com.hank.service.ChangeComboService;
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("changeComboAction")
@Scope("prototype")
public class ChangeComboAction extends GenericAction{

    @Autowired
    @Qualifier("changeComboService")
    private ChangeComboService changeComboService;

    @Action(value="/changeCombo" , results={@Result(name="success",location="changeCombo.jsp"),
                                            @Result(name="input",location="login.jsp")})
    public String execute(){
        // 有要AOP的記得 service.setAction(this)  設置 result = input
        Map<String, Object> request = this.getRequest();
        RealCombo comboToBeChanged = (RealCombo)request.get("comboToBeChanged");
        changeComboService.setAction(this);
        return changeComboService.showSameCategory(comboToBeChanged);
    }
    
    
    private String new_desc;
    private int quantity;
    private int cartId_comboToBeChanged;

    @Action(value="/changeCombo_submit" , results={@Result(name="success",location="order",type="redirectAction"),
                                                  @Result(name="input",  location="login.jsp")})
    public String changeCombo_submit(){
        // 有要AOP的記得 service.setAction(this)  設置 result = input
        changeComboService.setAction(this);
        return changeComboService.changeCombo_submit(cartId_comboToBeChanged, new_desc, quantity);
    }
    
    
    
    
    
    private Map<String, Object> getRequest(){
        ActionContext ctx = ActionContext.getContext();
        return (Map<String, Object>)ctx.get("request");
    }
    public String getNew_desc() {
        return new_desc;
    }

    public void setNew_desc(String new_desc) {
        this.new_desc = new_desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    public int getCartId_comboToBeChanged() {
        return cartId_comboToBeChanged;
    }

    public void setCartId_comboToBeChanged(int cartId_comboToBeChanged) {
        this.cartId_comboToBeChanged = cartId_comboToBeChanged;
    }
}
