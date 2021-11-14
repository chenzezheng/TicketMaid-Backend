package com.whu.ticket.controller;

import com.alibaba.fastjson.JSONObject;
import com.whu.ticket.annotation.AdminLogin;
import com.whu.ticket.annotation.UserLogin;
import com.whu.ticket.entity.Comment;
import com.whu.ticket.pojo.Result;
import com.whu.ticket.service.CommentService;
import com.whu.ticket.util.JwtUtil;
import com.whu.ticket.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/query")
    public Result queryComment(HttpServletRequest request) {
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        int total = commentService.countComment(eventId);
        if ((pageNo-1)*pageSize >= total) {
            return new Result(-1, null, "页码超出范围");
        }
        List<CommentVO> comments = commentService.queryComment(eventId, pageNo, pageSize);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", total);
        jsonObject.put("comments", comments);
        return new Result(0, jsonObject, "查询成功");
    }

    @UserLogin
    @PostMapping("/add")
    public Result addComment(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int userId = JwtUtil.getUserID(token);
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        int stars = Integer.parseInt(request.getParameter("stars"));
        String content = request.getParameter("content");
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setEventId(eventId);
        comment.setStars(stars);
        comment.setTime(new Date());
        comment.setContent(content);
        commentService.addComment(comment);
        return new Result(0, null, "评论成功");
    }
}
