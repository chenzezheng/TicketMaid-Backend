package com.whu.ticket.dao;

import com.whu.ticket.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectByUsername(String username);
    User selectById(int id);
    void insertUser(User user);
    void updateUserPassword(User user);
    void updateUserProfile(User user);
}
