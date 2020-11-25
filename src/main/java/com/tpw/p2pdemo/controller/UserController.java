package com.tpw.p2pdemo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpw.p2pdemo.pojo.User;
import com.tpw.p2pdemo.service.UserService;
import com.tpw.p2pdemo.tools.CaptchaUtil;
import com.tpw.p2pdemo.tools.MD5Utils;
import com.tpw.p2pdemo.vo.UserLoginVo;
import com.tpw.p2pdemo.vo.UserPagingVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Api(description = "用户操作")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation(value = "查询全体用户")
    @GetMapping("/listAll")
    public Object listAll(){
        return userService.listAll();
    }

    @ApiOperation(value = "验证码地址")
    @GetMapping("/getCaptcha")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成图片验证码
        BufferedImage image = CaptchaUtil.createImage();
        // 生成文字验证码
        String randomText = CaptchaUtil.drawRandomText(image);
        // 保存到验证码到 redis 有效期两分钟
        String temp = UUID.randomUUID().toString();
        HttpSession session = request.getSession();
        session.setAttribute("flag",temp);
        redisTemplate.opsForValue().set( temp, randomText.toLowerCase(), 2, TimeUnit.MINUTES);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpeg", out);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public String login(@RequestBody UserLoginVo userLoginVo, HttpServletRequest request){
        userLoginVo.setPassword(MD5Utils.toMD5(userLoginVo.getPassword()));
        String flag = (String) request.getSession().getAttribute("flag");
        request.removeAttribute("flag");
        if (userLoginVo.getCaptch().equalsIgnoreCase((String) redisTemplate.opsForValue().get(flag))){
            String uuidToken= UUID.randomUUID().toString();
            uuidToken=uuidToken.replace("-","");
            if(userService.login(userLoginVo)){
                redisTemplate.opsForValue().set(uuidToken,userLoginVo.getUsername(),7,TimeUnit.DAYS);
                return uuidToken;
            }
        }
        else { return "验证码错误,登录失败"; }

        return "登录失败";
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public boolean register(@RequestBody User user){
        user.setPassword(MD5Utils.toMD5(user.getPassword()));
        return  userService.adduser(user)>0;
    }

    @ApiOperation(value = "以电话号查询用户")
    @GetMapping("/getUserByPhone")
    public Object getUserByPhone(@RequestBody User user){
        return  userService.getUserByPhone(user);
    }

    @ApiOperation(value = "根据id删除用户")
    @PostMapping("/deleteUserById")
    public boolean deleteUserById(@RequestBody User user){
        return  userService.deleteUserById(user)>0;
    }

    @ApiOperation(value = "用户分页",notes = "可同时根据电话号码筛选用户")
    @GetMapping("/userPaging")
    public Object userPaging(@RequestBody UserPagingVo userPagingVo){
        PageHelper.startPage(userPagingVo.getPageNum(),userPagingVo.getPageSize());
        List<User> userList = userService.userPaging(userPagingVo);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return pageInfo;
    }

}
