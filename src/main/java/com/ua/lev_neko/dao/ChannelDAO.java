package com.ua.lev_neko.dao;

import com.ua.lev_neko.models.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelDAO extends JpaRepository<Channel,Integer> {
    String findByName(String name);
}
