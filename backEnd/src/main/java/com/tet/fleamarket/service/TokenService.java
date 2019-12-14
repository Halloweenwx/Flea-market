package com.tet.fleamarket.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tet.fleamarket.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Hou Weiying
 * @date 2019-12-10 12:43
 */
@Service
public class TokenService {

    @Value("${values.service.tokenService.expireSecond}")
    int expireSecond;

    public String genUserToken(User user){
        return JWT.create().withAudience(user.getUid())
                .withClaim("uid",user.getUid())
                .withClaim("username",user.getUsername())
                .withClaim("expireSecond",expireSecond)
                .sign(Algorithm.HMAC256(user.getUid()));

    }
}
