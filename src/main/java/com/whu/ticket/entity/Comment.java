package com.whu.ticket.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int id;
    private int userId;
    private int eventId;
    private Date time;
    private int stars;
    private String content;
}
