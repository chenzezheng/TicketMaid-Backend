package com.whu.ticket.controller;

import com.alibaba.fastjson.JSONObject;
import com.whu.ticket.annotation.RefreshToken;
import com.whu.ticket.pojo.Result;
import com.whu.ticket.util.JwtUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api/token")
public class TokenController {
    @RefreshToken
    @GetMapping("/refresh")
    public Result refresh(HttpServletRequest request) {
        String refresh_token = request.getHeader("refresh_token");
        try {
            int userId = JwtUtil.getUserID(refresh_token);
            final long seven_days = 604800000;
            final long one_day = 86400000;
            String new_access_token = JwtUtil.createToken(String.valueOf(userId), one_day, "access");
            String new_refresh_token = JwtUtil.createToken(String.valueOf(userId), seven_days, "refresh");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("access_token", new_access_token);
            jsonObject.put("refresh_token", new_refresh_token);
            return new Result(0, jsonObject, "刷新成功");
        } catch (RuntimeException e) {
            return new Result(-1, null, "刷新失败:" + e.getMessage());
        }
    }
}
