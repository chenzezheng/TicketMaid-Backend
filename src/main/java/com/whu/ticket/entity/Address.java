package com.whu.ticket.entity;

import lombok.Data;

@Data
public class Address {
    private int id;
    private int user_id;
    private String address;
    private String name;
    private String phone;
}
