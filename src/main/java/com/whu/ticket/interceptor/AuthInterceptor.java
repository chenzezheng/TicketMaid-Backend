package com.whu.ticket.interceptor;

import com.whu.ticket.annotation.UserLogin;
import com.whu.ticket.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {

    private boolean check(String token) {
        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }
        return JwtUtil.verifyToken(token);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access_token");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(UserLogin.class)) {
            UserLogin userLogin = method.getAnnotation(UserLogin.class);
            if (userLogin.required()) return check(token);
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
