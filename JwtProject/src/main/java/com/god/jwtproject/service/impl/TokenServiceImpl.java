package com.god.jwtproject.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.god.jwtproject.base.User;
import com.god.jwtproject.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/19 17:12
 * @ClassName: TokenServiceImpl
 * @Description:
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId())// 将 user id 保存到 token 里面
                .withExpiresAt(new Date(System.currentTimeMillis() + 60000)) // 设置60秒过期时间
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
