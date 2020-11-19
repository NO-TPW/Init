package com.tpw.p2pdemo.service;

import com.tpw.p2pdemo.pojo.User;
import com.tpw.p2pdemo.vo.UserLoginVo;
import com.tpw.p2pdemo.vo.UserPagingVo;

import java.util.List;


public interface UserService {

    boolean login(UserLoginVo userLoginVo);

    int adduser(User user);

    List<User> listAll();

    List<User> getUserByPhone(User user);

    int deleteUserById(User user);

    List<User> userPaging(UserPagingVo userPagingVo);

}
