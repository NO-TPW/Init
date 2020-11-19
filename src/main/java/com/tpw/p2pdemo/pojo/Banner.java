package com.tpw.p2pdemo.pojo;

import lombok.Data;

@Data
public class Banner {
    long id;
    long bannerType;
    String bannerTitle;
    String bannerimg;
    String create_time;
    int is_use;
    int terminal_type;
    int dr;
    String bannerUrl;
    int bannerno;

}
