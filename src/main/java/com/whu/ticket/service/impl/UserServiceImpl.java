package com.whu.ticket.service.impl;

import com.whu.ticket.dao.UserMapper;
import com.whu.ticket.pojo.User;
import com.whu.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) return null;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(password, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(password));
            userMapper.updateUserPassword(user);
            return user;
        };
        return null;
    }

    @Override
    public void register(User user) {
        User res = userMapper.selectByUsername(user.getUsername());
        if (res != null) {
            throw new RuntimeException("用户名已存在");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
    }

    @Override
    public void modifyProfile(User user) {
        userMapper.updateUserProfile(user);
    }

    @Override
    public void modifyPassword(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.updateUserPassword(user);
    }

    @Override
    public boolean isAdmin(int userId) {
        User res = userMapper.selectById(userId);
        if (res != null && res.getIs_admin() == 1) return true;
        throw new RuntimeException("无操作权限");
    }
}
