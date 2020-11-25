package com.whu.ticket.service;

import com.whu.ticket.pojo.User;

public interface UserService {
    public boolean login(String username, String password);
    public void register(User user);
}
