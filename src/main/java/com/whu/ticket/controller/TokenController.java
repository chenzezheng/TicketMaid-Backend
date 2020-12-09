package com.whu.ticket.controller;

import com.alibaba.fastjson.JSONObject;
import com.whu.ticket.pojo.Result;
import com.whu.ticket.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/token")
public class TokenController {
    @GetMapping("/refresh")
    public Result refresh(HttpServletRequest request) {
        String refresh_token = request.getHeader("refresh_token");
        try {
            if (JwtUtil.verifyToken(refresh_token)) {
                int userId = JwtUtil.getUserID(refresh_token);
                final long seven_days = 604800000;
                final long two_hours = 7200000;
                String new_access_token = JwtUtil.createToken(String.valueOf(userId), two_hours);
                String new_refresh_token = JwtUtil.createToken(String.valueOf(userId), seven_days);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("access_token", new_access_token);
                jsonObject.put("refresh_token", new_refresh_token);
                return new Result(0, jsonObject, "刷新成功");
            }
        } catch (RuntimeException e) {
            return new Result(-1, null, "刷新失败:" + e.getMessage());
        }
        return new Result(-1, null, "刷新失败");
    }
}
