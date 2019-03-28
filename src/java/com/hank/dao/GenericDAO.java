package com.hank.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("genericDAO")
@Transactional
public abstract class GenericDAO<T> {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate(){
        return this.hibernateTemplate;
    }
    public void saveOrUpdate(T object) {
        if(object == null){
            throw new RuntimeException("saveOrUpdate的物件為空");
        }
        getHibernateTemplate().saveOrUpdate(object);
    }
    
    public void delete(T object){
        if(object == null){
            throw new RuntimeException("delete的物件為空");
        }
        getHibernateTemplate().delete(object);
    }
    
    
}
