package com.whu.ticket.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {
    private int id;
    private String event_name;
    private Date event_time;
    private String event_cover;
    private double event_price;
    private int order_quantity;
    private Date order_time;
    private String user_name;
    private String user_address;
    private String user_phone;
}
