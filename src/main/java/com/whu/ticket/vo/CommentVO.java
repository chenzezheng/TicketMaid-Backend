package com.whu.ticket.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    private int id;
    private String nickname;
    private Date time;
    private int stars;
    private String content;
}
