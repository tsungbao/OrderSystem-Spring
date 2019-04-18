package com.hank.dao;

import com.hank.domain.cart.Cart;
import com.hank.domain.item.Combo;
import com.hank.domain.item.RealCombo;
import hibernate_pojo.Merchandise;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("merchandiseDAO")
public class MerchandiseDAO extends GenericDAO<Merchandise> {

    public List getMerchandise_items() {
        String hql = "from Merchandise m where m.merchandiseDescription is null and m.saleable = true";
        return getHibernateTemplate().find(hql);
    }

    public List getMerchandise_combos() {
        String hql = "from Merchandise m where m.merchandiseDescription is not null and m.saleable = true";
        return getHibernateTemplate().find(hql);
    }

    //套餐組合中的單品也要記得加到
    //做到批量處理
    //預編語句
    public void submit_merchandise(Cart cart) {
        String hql = "update Merchandise m set m.bought = m.bought+? where m.merchandiseName = ? ; ";
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //預編語句
        Query query = session.createQuery(hql);
        Map<Combo, Integer> cartItems = cart.showCartItems();

        Iterator< Map.Entry<Combo, Integer>> it = cartItems.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Combo, Integer> entry = it.next();
            if (entry.getKey() instanceof RealCombo) {
                //此cartItem 是套餐
                RealCombo cartItem = (RealCombo) entry.getKey();
                int bought = entry.getValue();

                
                 //循環去加cartItem裡面的套餐組成的被購次數
                ArrayList<String> desc = cartItem.getDescription();
                for (String content_name : desc) {        
                    this.getHibernateTemplate().execute(new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session sn) throws HibernateException {
                            return query.setInteger(0, bought).setString(1, content_name).executeUpdate();
                        }

                    });
                }

                this.getHibernateTemplate().execute(new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session sn) throws HibernateException {
                        return query.setInteger(0, bought).setString(1, cartItem.getName()).executeUpdate();
                    }

                });

            } else {
                //此cartItem 是單品
                Combo cartItem = entry.getKey();
                int bought = entry.getValue();
                this.getHibernateTemplate().execute(new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session sn) throws HibernateException {
                        return query.setInteger(0, bought).setString(1, cartItem.getName()).executeUpdate();
                    }

                });
            }
        }

    }
}
