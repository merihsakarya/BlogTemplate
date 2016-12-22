package com.blog.template.dao;

import com.blog.template.entity.Customer;

public interface CustomerDao {  
    
    public Customer getCustomerByEmail(String email);
    public Customer getCustomerByUsernameOrEmail(String param);
    
}
