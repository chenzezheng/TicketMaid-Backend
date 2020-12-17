package com.whu.ticket.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Event {
    private int id;
    private String name;
    private int host_id;
    private Date time;
    private String location;
    private int quota;
    private String info;
    private String cover;
    private double price;
}
