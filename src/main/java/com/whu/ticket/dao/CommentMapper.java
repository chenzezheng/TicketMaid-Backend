package com.whu.ticket.dao;

import com.whu.ticket.entity.Comment;
import com.whu.ticket.vo.CommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    void insertComment(Comment comment);
    int countComment(int eventId);
    List<CommentVO> selectByEventId(int eventId, int offset, int limit);
}
