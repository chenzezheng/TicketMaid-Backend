package com.whu.ticket.interceptor;

import com.whu.ticket.annotation.AdminLogin;
import com.whu.ticket.annotation.RefreshToken;
import com.whu.ticket.annotation.UserLogin;
import com.whu.ticket.controller.OrderController;
import com.whu.ticket.service.UserService;
import com.whu.ticket.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    UserService userService;

    private boolean check(String token) {
        if (StringUtils.isBlank(token)) {
            throw new RuntimeException("无token，请重新登录");
        }
        return JwtUtil.verifyToken(token);
    }

    private boolean isAdmin(String token) {
        int userId = JwtUtil.getUserID(token);
        return userService.isAdmin(userId);
    }

    private boolean isRefresh(String token) {
        return JwtUtil.getTokenType(token).equals("refresh");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("method:{}", request.getMethod());
        String access_token = request.getHeader("access_token");
        String refresh_token = request.getHeader("refresh_token");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(UserLogin.class)) {
            UserLogin userLogin = method.getAnnotation(UserLogin.class);
            if (userLogin.required()) return check(access_token);
        } else if (method.isAnnotationPresent(AdminLogin.class)) {
            AdminLogin adminLogin = method.getAnnotation(AdminLogin.class);
            if (adminLogin.required()) return (check(access_token) && isAdmin(access_token));
        } else if (method.isAnnotationPresent(RefreshToken.class)) {
            RefreshToken refreshToken = method.getAnnotation(RefreshToken.class);
            if (refreshToken.required()) return (check(refresh_token) && isRefresh(refresh_token));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
