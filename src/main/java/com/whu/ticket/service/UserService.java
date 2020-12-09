package com.whu.ticket.service;

import com.whu.ticket.VO.UserVO;
import com.whu.ticket.pojo.User;

public interface UserService {
    public User login(String username, String password);
    public void register(User user) throws Exception;
    public void modifyProfile(User user);
    public void modifyPassword(User user);
}
