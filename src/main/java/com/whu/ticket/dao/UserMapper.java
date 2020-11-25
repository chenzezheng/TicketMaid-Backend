package com.whu.ticket.dao;

import com.whu.ticket.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int insert(User user);
    User selectByUsername(String username);
    User selectById(int id);
}
