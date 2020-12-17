package com.whu.ticket.vo;

import lombok.Data;

@Data
public class OrderVO {
    private int id;
    private String name;
    private String cover;
    private double price;
    private int quantity;
}
