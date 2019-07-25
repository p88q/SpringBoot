package com.god.jwtproject.base;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/19 14:05
 * @ClassName: User
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String userName;
    private String password;

    /**
     * 获取token
     * @param user
     * @return
     */
    public String getToken(User user) {
        /**
         * Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
         * withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
         */
        String token = "";
        token = JWT.create().withJWTId(user.getId()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
