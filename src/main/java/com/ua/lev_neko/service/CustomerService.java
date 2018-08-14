package com.ua.lev_neko.service;

import com.ua.lev_neko.models.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    void save(Customer customer);
}
