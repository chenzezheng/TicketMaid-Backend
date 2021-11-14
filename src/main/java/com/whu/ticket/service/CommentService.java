package com.whu.ticket.service;

import com.whu.ticket.entity.Comment;
import com.whu.ticket.vo.CommentVO;

import java.util.List;

public interface CommentService {
    public int countComment(int eventId);
    public List<CommentVO> queryComment(int eventId, int pageNo, int pageSize);
    public void addComment(Comment comment);
}
