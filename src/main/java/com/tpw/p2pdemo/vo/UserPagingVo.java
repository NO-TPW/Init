package com.tpw.p2pdemo.vo;

import com.tpw.p2pdemo.pojo.User;
import lombok.Data;

@Data
public class UserPagingVo extends User {
    int pageSize = 5;
    int pageNum = 1;
}
