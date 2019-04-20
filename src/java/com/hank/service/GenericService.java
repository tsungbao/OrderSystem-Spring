package com.hank.service;

import com.hank.web.action.GenericAction;
import com.opensymphony.xwork2.ActionContext;
import hibernate_pojo.Member;
import java.util.Map;

public abstract class GenericService {
   private GenericAction action;

    public GenericAction getAction() {
        return action;
    }

    public void setAction(GenericAction action) {
        this.action = action;
    }

    protected Member getMemberFromSession(){
        ActionContext ctx = ActionContext.getContext();
        Map<String,Object> session = ctx.getSession();
        return (Member)session.get("member");
    }
   
}
