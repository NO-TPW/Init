package com.tpw.p2pdemo.pojo;

import lombok.Data;

@Data
public class User {
    long userid;
    String username;
    String password;
    String phone;
    int dr;
    String createTime;
    String qqcode;
    String qqnickname;
}
