package com.whu.ticket.entity;

import com.whu.ticket.vo.UserVO;
import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private int sex;
    private String email;
    private int is_admin;

    public UserVO toValueObject() {
        UserVO vo = new UserVO(id, username, nickname, sex, email, is_admin);
        return vo;
    }
}
