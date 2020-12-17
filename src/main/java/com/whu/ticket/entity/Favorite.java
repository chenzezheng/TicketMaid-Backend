package com.whu.ticket.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Favorite {
    private int id;
    private int user_id;
    private int event_id;
    private Date time;
}
