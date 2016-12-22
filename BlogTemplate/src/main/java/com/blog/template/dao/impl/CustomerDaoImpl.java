package com.blog.template.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blog.template.dao.CustomerDao;
import com.blog.template.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private EntityManager entityManager;
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public Customer getCustomerByUsernameOrEmail(String param) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT e FROM Customer e where e.username = ?1 or e.email = ?2", Customer.class);
        query.setParameter(1, param);
        query.setParameter(2, param);
        
        List<Customer> customers = query.getResultList();
        
        if(customers.size()>0)
            return customers.get(0);
        else
            return null;
    }
    
    @Transactional
    public Customer getCustomerByEmail(String email) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT e FROM Customer e where e.email = ?1", Customer.class);
        query.setParameter(1, email);
        
        List<Customer> customers = query.getResultList();
        
        if(customers.size()>0)
            return customers.get(0);
        else
            return null;
    }
    
}
