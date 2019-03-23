package com.hank.dao;

import com.hank.domain.item.Combo;
import hibernate_pojo.Merchandise;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("merchandiseDAO")
public class MerchandiseDAO implements Dao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate ;
    
    public List getMerchandise_items(){
        String hql = "from Merchandise m where m.merchandiseDescription is null and m.saleable = true";
        return  hibernateTemplate.find(hql);
    }
    
    public List getMerchandise_combos(){
        String hql = "from Merchandise m where m.merchandiseDescription is not null and m.saleable = true";
        return  hibernateTemplate.find(hql);
    }
}
