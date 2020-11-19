package com.tpw.p2pdemo.pojo;

import lombok.Data;

@Data
public class UserM {
    long userIDM;
    String usernameM;
    String passwordM;
    String phone;
    String email;
    int roleid;
    String createTime;
    int dr;
}
