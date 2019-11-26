package com.god.example.controller;

import com.god.example.model.UserBean;
import com.god.example.service.UserService;
import com.god.util.img.SCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 18:29
 * @ClassName: UserController
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/queryPage")
    public PageData<UserBean> queryPageList(Integer pageNum, Integer pageSize) {
        return userService.queryPageList(new UserBean(), pageNum, pageSize);
    }

    /**
     * @author jiaqing.xu@hand-china.com
     * @date 2017/8/23
     * @description 生成图片验证码
     */
    @RequestMapping(value = "/userInfo/verification")
    public void verification(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //实例生成验证码对象
        SCaptcha instance = new SCaptcha();
        //将验证码存入session
        session.setAttribute("verification", instance.getCode());
        //向页面输出验证码图片
        instance.write(response.getOutputStream());
    }

}
