package com.hank.dao;

import com.hank.domain.cart.Cart;
import com.hank.domain.item.Combo;
import hibernate_pojo.Member;
import hibernate_pojo.Merchandise;
import hibernate_pojo.Order;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("orderDAO")
@Transactional
public class OrderDAO extends GenericDAO<Order> {
    public void submit_order(Member member) {
        Cart cart = member.getCart();
        Map<Combo, Integer> cartItems = cart.showCartItems();
        int maxId = getMaxId_orderTable() + 1;
    
        
        Iterator< Map.Entry<Combo, Integer>> it = cartItems.entrySet().iterator();       
        while (it.hasNext()) {
            Map.Entry<Combo, Integer> entry = it.next();
            
            Combo item_inEntry = entry.getKey();
            int quantity = entry.getValue();
            
            Order order = new Order();
            order.setMember(member);
            order.setConsumptionAmount(item_inEntry.getPrice()*quantity);
            order.setOrderId(maxId);
            order.setQuantity(quantity);
            order.setTime(new Date());
            Merchandise mer = getHibernateTemplate().load(Merchandise.class, item_inEntry.getId()) ;// orderDAO這邊還需要加一個order.setMerchandise()
            order.setMerchandise(mer);
            
            
            getHibernateTemplate().saveOrUpdate(order);
        }

    }

    private int getMaxId_orderTable() {
        String hql = "select max(orderId) from Order";
        return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session sn) throws HibernateException {
                return (int) sn.createQuery(hql).uniqueResult();
            }
        });
    }
}
