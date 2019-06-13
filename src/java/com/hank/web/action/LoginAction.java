
package com.hank.web.action;

import com.hank.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.*;



@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends GenericAction{
    @Autowired
    @Qualifier("loginService")
    private LoginService loginService;
    
    private String account="";
    private String password="";

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    @Action(value="/login", results={ @Result(name="input",location="login.jsp") , 
                                      @Result(name="success",location="/main.action" , type="redirectAction") })
    public String execute(){
        String result="";
        try{
            result = loginService.login(account, password);
        }catch(Exception e){
            this.addActionError(e.getMessage());
            result = com.opensymphony.xwork2.Action.INPUT;
        }
        return result;
    }
}
