package com.tpw.p2pdemo.vo;

import lombok.Data;

@Data
public class UserLoginVo {
    String username;
    String password;
    String captch;
}
