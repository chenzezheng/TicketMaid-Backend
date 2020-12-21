package com.whu.ticket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVO {
    private int id;
    private String username;
    private String nickname;
    private int sex;
    private String email;
    private int is_admin;
}
