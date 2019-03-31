package com.hank.service;

import com.hank.dao.MemberDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import hibernate_pojo.Member;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginService extends GenericService {

    public String login(String account, String password) throws RuntimeException {
        Member member = new MemberDAO().findMember(account, password);
        if (member == null) {
            // login 失敗
            throw new RuntimeException("帳號/密碼有誤");
        } else {
            // login之後得出來的那個member放進session作用域裡面       
            ActionContext ctx = ActionContext.getContext();
            Map<String, Object> session = ctx.getSession();

            session.put("member", member);
        }
        return Action.SUCCESS;
    }
}
