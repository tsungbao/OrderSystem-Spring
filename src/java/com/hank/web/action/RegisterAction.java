package com.hank.web.action;

import com.hank.service.RegisterService;
import hibernate_pojo.Member;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("registerAction")
@Scope("prototype")
public class RegisterAction extends GenericAction {

    @Autowired
    @Qualifier("registerService")
    private RegisterService registerService;

    private Member member;
    private String password_confirmed;

    @Action(value = "/register", results = {
        @Result(name = "input", location = "register.jsp"), 
        @Result(name = "success", location = "/main.action" , type="redirectAction")})
    public String execute() {
        // 有要AOP的記得 service.setAction(this)
        String result = "";
        try {
            registerService.setAction(this);
            result = registerService.register(member);
        } catch (Exception e) {
            this.addActionError(e.getMessage());
            result = "input";
        }
        return result;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getPassword_confirmed() {
        return password_confirmed;
    }

    public void setPassword_confirmed(String password_confirmed) {
        this.password_confirmed = password_confirmed;
    }
}
