package com.whu.ticket.pojo;

import com.whu.ticket.VO.UserVO;
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
        UserVO vo = new UserVO();
        vo.setId(id);
        vo.setNickname(nickname);
        vo.setUsername(username);
        return vo;
    }
}
