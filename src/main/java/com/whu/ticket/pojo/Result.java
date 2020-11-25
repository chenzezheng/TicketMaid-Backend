package com.whu.ticket.pojo;

import lombok.Data;

@Data
public class Result {
    private int code;
    private Object data;
    private String desc;

    public Result (int code, Object data, String desc) {
        this.code = code;
        this.data = data;
        this.desc = desc;
    }
}
