package com.ua.lev_neko.service;

import com.ua.lev_neko.models.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface CustomerService extends UserDetailsService {

    void save(Customer customer);

    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;

    UserDetails loadUserById(int id);

    UserDetails loadByCode(String code);



}
