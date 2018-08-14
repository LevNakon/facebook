package com.ua.lev_neko.service;

import com.ua.lev_neko.dao.CustomerDAO;
import com.ua.lev_neko.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return customerDAO.findByUsername(s);
    }
}
