package com.tpw.p2pdemo.service;

import com.tpw.p2pdemo.mapper.UserMapper;
import com.tpw.p2pdemo.pojo.User;
import com.tpw.p2pdemo.vo.UserLoginVo;
import com.tpw.p2pdemo.vo.UserPagingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserMapper userMapper;

    public boolean login(UserLoginVo userLoginVo) {
        return userMapper.login(userLoginVo);
    }

    public int adduser(User user) {
        return userMapper.adduser(user);
    }

    public List<User> listAll() {
        return userMapper.listAll();
    }

    public List<User> getUserByPhone(User user) {
        return userMapper.getUserByPhone(user);
    }

    public int deleteUserById(User user){
        return userMapper.deleteUserById(user);
    }

    public List<User> userPaging(UserPagingVo userPagingVo) {
        return userMapper.userPaging(userPagingVo);
    }

}
