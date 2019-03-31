package com.hank.service;

import com.hank.dao.MemberDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import hibernate_pojo.Member;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("registerService")
public class RegisterService extends GenericService {

    private boolean checkWhetherDuplicateAccount(String account) {
        Member member = new MemberDAO().findMember(account);

        if (member == null) {
            // 註冊允許，沒有重複account
            return false;
        } else {
            //註冊不允許，因為已經有人用同樣的account註冊過了
            return true;
        }
    }

    private void byDefault(Member member) {
        member.setConsumptionTimes(0);
        member.setTotalConsumption(0);
        member.setMemberStatus("member");
    }

    public String register(Member member) throws RuntimeException {
        boolean isDuplicate = this.checkWhetherDuplicateAccount(member.getAccount());

        if (isDuplicate) {
            throw new RuntimeException("已經有人使用過相同帳號了");
        } else {
            this.byDefault(member);
            //將member存進資料庫裏面
            new MemberDAO().saveOrUpdate(member);
            
            // 將 member 存進session裡面
            ActionContext ctx = ActionContext.getContext();
            Map<String, Object> session = ctx.getSession();
            session.put("member", member);
        }
        return Action.SUCCESS;
    }
}
