package com.whu.ticket.controller;

import com.whu.ticket.pojo.Result;
import com.whu.ticket.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public Result register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
//        userService.register();
        log.info("register fail");
        Result result = new Result(-1, null, "未知错误");
        return result;
    }

    @PostMapping("/login")
    public Object login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        log.info("login username: {} password: {}", username, password);
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            log.info("login fail");
            Result result = new Result(-1, null, "用户名或密码为空");
            return result;
        }

        boolean res = userService.login(username, password);
        if (res) {
            log.info("login success");
            Result result = new Result(0, null, "登录成功");
            return result;
        }
        log.info("login fail");
        Result result = new Result(-1, null, "未知错误");
        return result;
    }
}
