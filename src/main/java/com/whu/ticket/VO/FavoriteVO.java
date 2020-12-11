package com.whu.ticket.vo;

import lombok.Data;

import java.util.Date;

@Data
public class FavoriteVO {
    private int id;
    private int user_id;
    private int event_id;
    private Date time;
    private String name;
    private double price;
    private String cover;
}
