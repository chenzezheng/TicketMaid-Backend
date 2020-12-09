package com.whu.ticket.controller;

import com.alibaba.fastjson.JSONObject;
import com.whu.ticket.annotation.AdminLogin;
import com.whu.ticket.annotation.UserLogin;
import com.whu.ticket.pojo.Result;
import com.whu.ticket.pojo.User;
import com.whu.ticket.service.UserService;
import com.whu.ticket.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setPassword(password);
        user.setNickname(username);
        user.setUsername(username);
        user.setSex(2);
        try {
            userService.register(user);
            return new Result(0, user.toValueObject(), "注册成功");
        } catch(Exception e) {
            log.info("register fail");
            return new Result(-1, null, e.getMessage());
        }
    }

    @PostMapping("/login")
    public Result login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("login username: {} password: {}", username, password);
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            log.info("login fail");
            return new Result(-1, null, "用户名或密码为空");
        }

        User res = userService.login(username, password);
        if (res != null) {
            log.info("login success");
            final long seven_days = 604800000;
            final long two_hours = 7200000;
            String access_token = JwtUtil.createToken(String.valueOf(res.getId()), two_hours);
            String refresh_token = JwtUtil.createToken(String.valueOf(res.getId()), seven_days);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("access_token", access_token);
            jsonObject.put("refresh_token", refresh_token);
            jsonObject.put("user", res.toValueObject());
            return new Result(0, jsonObject, "登录成功");
        }
        log.info("login fail");
        return new Result(-1, null, "用户名或密码错误");
    }

    @UserLogin
    @PutMapping("/modify/profile")
    public Result modifyProfile(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int userId = JwtUtil.getUserID(token);
        String nickname = request.getParameter("nickname");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        User newUser = new User();
        newUser.setId(userId);
        newUser.setNickname(nickname);
        newUser.setSex(Integer.parseInt(sex));
        newUser.setEmail(email);
        userService.modifyProfile(newUser);
        return new Result(0, null, "修改资料成功");
    }

    @UserLogin
    @PutMapping("/modify/password")
    public Result modifyPassword(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int userId = JwtUtil.getUserID(token);
        String newPassword = request.getParameter("password");
        User newUser = new User();
        newUser.setId(userId);
        newUser.setPassword(newPassword);
        userService.modifyPassword(newUser);
        return new Result(0, null, "修改密码成功");
    }
}
