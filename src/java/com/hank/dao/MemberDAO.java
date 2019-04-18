
package com.hank.dao;

import hibernate_pojo.Member;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("memberDAO")
public class MemberDAO extends GenericDAO<Member> {
    
    // uniqueResult
    public Member findMember(String account, String password){
        return this.getHibernateTemplate().execute(new HibernateCallback<Member>(){
            @Override
            public Member doInHibernate(Session sn) throws HibernateException {
                return (Member)sn.createQuery("from Member m where m.account=? and m.password=?;")
                                 .setString(0, account).setString(1,password).uniqueResult();
            }            
        });
    }
    // uniqueResult
    public Member findMember(String account){
        return this.getHibernateTemplate().execute(new HibernateCallback<Member>(){
            @Override
            public Member doInHibernate(Session sn) throws HibernateException {
                return (Member)sn.createQuery("from Member m where m.account=?")
                                 .setString(0, account).uniqueResult();
            }            
        });
    }   
    // 此次購物總金額                                         
    public void submit_member(Member member, int totalConsumption){
        member.setConsumptionTimes(member.getConsumptionTimes()+1);
        int current_totalConsumption = member.getTotalConsumption();
        member.setTotalConsumption(totalConsumption + current_totalConsumption);
        this.saveOrUpdate(member);
    }
}
