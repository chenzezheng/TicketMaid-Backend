package com.whu.ticket.service.impl;

import com.whu.ticket.dao.UserMapper;
import com.whu.ticket.pojo.User;
import com.whu.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) return false;
        if (password.equals(user.getPassword())) return true;
        return false;
    }

    @Override
    public void register(User user) {
        userMapper.insert(user);
    }
}
