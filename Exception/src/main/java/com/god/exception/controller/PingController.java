package com.god.exception.controller;

import com.god.exception.model.User;
import com.god.exception.service.UserService;
import com.god.util.Constants;
import com.god.util.LocaleMessageUtil;
import com.god.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/12 17:49
 * @ClassName: PingController
 * @Description:
 */
@RestController
public class PingController {

    @Autowired
    private UserService userService;

    /**
     * 测试传递的json经过fastjson解析{ "age": 0, "dier": false, "message": [], "name": "" }
     * @param user
     * @return 返回数据格式经过fastjson处理了{ "age": 0, "dier": false, "message": [], "name": "" }
     */
    @PostMapping("/ping")
    public Result ping(@RequestBody User user) {
        System.out.println(user);
        User user2 = new User();
        long l = 9223372036854775807L;
        user2.setMoney(l);
        return Result.getSuccess(Constants.MESSAGE_SUCCESS, user2);
    }

    @RequestMapping("/ping2")
     public Result ping2() {
        return userService.ping2(new User());
     }

    @RequestMapping("/ping3")
     public Result ping3() {
        return userService.ping3();
     }

    @RequestMapping("/ping4")
    public Result ping4() {
        int i = 3/0;
        return Result.getSuccess();
    }

    @RequestMapping("/ping5")
    public String ping5() {
        return LocaleMessageUtil.getMessage("hello");
    }
}
