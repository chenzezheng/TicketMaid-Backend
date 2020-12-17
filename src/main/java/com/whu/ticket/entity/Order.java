package com.whu.ticket.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private int id;
    private int user_id;
    private int event_id;
    private Date time;
    private int quantity;
    private String name;
    private String address;
    private String phone;
}
