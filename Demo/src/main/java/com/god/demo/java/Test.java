package com.god.demo.java;

import com.alibaba.fastjson.JSONObject;
import com.god.user.bean.UserInfoBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/28 10:22
 * @ClassName: Test
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        String str = "[[\"123\"], [\"321\"]]";

        Object parse = JSONObject.parse(str);
    }

}
