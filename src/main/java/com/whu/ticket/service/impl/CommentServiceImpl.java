package com.whu.ticket.service.impl;

import com.whu.ticket.dao.CommentMapper;
import com.whu.ticket.entity.Comment;
import com.whu.ticket.service.CommentService;
import com.whu.ticket.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public int countComment(int eventId) {
        return commentMapper.countComment(eventId);
    }

    @Override
    public List<CommentVO> queryComment(int eventId, int pageNo, int pageSize) {
        return commentMapper.selectByEventId(eventId, (pageNo-1)*pageSize, pageSize);
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.insertComment(comment);
    }
}
