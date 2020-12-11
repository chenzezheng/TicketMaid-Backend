package com.whu.ticket.controller;

import com.whu.ticket.annotation.UserLogin;
import com.whu.ticket.pojo.Favorite;
import com.whu.ticket.pojo.Result;
import com.whu.ticket.service.FavoriteService;
import com.whu.ticket.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    private static final Logger log = LoggerFactory.getLogger(FavoriteController.class);

    @Autowired
    FavoriteService favoriteService;

    @UserLogin
    @GetMapping("/query")
    public Result queryFavorites(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int userId = JwtUtil.getUserID(token);
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        if (pageNo <= 0 || pageSize <= 0) {
            return new Result(-1, null, "参数错误");
        }
        List<Favorite> favorites = favoriteService.queryFavorites(userId, pageNo, pageSize);
        return new Result(0, favorites, "查询收藏成功");
    }

    @UserLogin
    @PostMapping("/add")
    public Result addFavorite(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int userId = JwtUtil.getUserID(token);
        int eventId = Integer.parseInt(request.getParameter("event_id"));
        Date time = new Date();
        Favorite favorite = new Favorite();
        favorite.setUser_id(userId);
        favorite.setEvent_id(eventId);
        favorite.setTime(time);
        try {
            favoriteService.addFavorite(favorite);
            return new Result(0, favorite, "收藏成功");
        } catch (Exception e) {
            return new Result(-1, null, e.getMessage());
        }
    }

    @UserLogin
    @DeleteMapping("/remove")
    public Result removeFavorite(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        favoriteService.removeFavorite(id);
        return new Result(0, null, "取消收藏成功");
    }
}
