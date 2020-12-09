package com.whu.ticket.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private int code;
    private Object data;
//    private String token;
    private String desc;
}
