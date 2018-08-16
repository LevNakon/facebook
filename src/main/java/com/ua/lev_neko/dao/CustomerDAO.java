package com.ua.lev_neko.dao;

import com.ua.lev_neko.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {
    Customer findByUsername(String username);

    Customer findByEmail(String email);

    Customer findById(int id);
    //Customer deleteByAccountNonExpired(String accountNonExpired);
}
