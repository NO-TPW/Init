package com.tpw.p2pdemo.mapper;

import com.tpw.p2pdemo.pojo.User;
import com.tpw.p2pdemo.vo.UserLoginVo;
import com.tpw.p2pdemo.vo.UserPagingVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    boolean login(UserLoginVo userLoginVo);

    int adduser(User user);

    List<User> listAll();

    List<User> getUserByPhone(User user);

    int deleteUserById(User user);

    List<User> userPaging(UserPagingVo userPagingVo);

}
