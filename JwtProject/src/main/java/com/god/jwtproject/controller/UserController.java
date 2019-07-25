package com.god.jwtproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.god.jwtproject.annotation.UserLoginToken;
import com.god.jwtproject.base.User;
import com.god.jwtproject.service.TokenService;
import com.god.jwtproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/19 16:40
 * @ClassName: UserController
 * @Description: 用户相关controller层
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    /**
     * static保证对于每一个对象只存在一个logger，节省资源。
     * final是一种编程习惯，保证logger只是记录该类的信息
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    //登录
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findByUsername(user);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

}
