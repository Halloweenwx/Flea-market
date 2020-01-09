package com.tet.fleamarket.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.tet.fleamarket.entity.Administrator;
import com.tet.fleamarket.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Map;

/**
 * @author Hou Weiying
 * @date 2019-12-10 12:43
 */
@Service
public class TokenService {

    @Value("${values.service.tokenService.expireSecond}")
    int expireSecond;

    public String genUserToken(User user){
        String utype;
        if(user instanceof Administrator){
            utype = "A";
        }else{
            utype = "C";
        }
        return JWT.create().withAudience(user.getUid())
                .withClaim("uid",user.getUid())
                .withClaim("username",user.getUsername())
                .withClaim("utype",utype)
                .withClaim("expireSecond",expireSecond)
                .sign(Algorithm.HMAC256(user.getSalt()));

    }

    public User getUserFromToken(String token){
        User user = new User();
        Map<String, Claim> claims;
        String uid = JWT.decode(token).getAudience().get(0);
        claims = JWT.decode(token).getClaims();
        String username = claims.get("username").asString();
        user.setUid(uid);
        user.setUsername(username);
        return user;
    }
    public String getTokenFromCookies(Cookie[] cookies){
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }
    public User getUserFromCookies(Cookie[] cookies){
        return getUserFromToken(getTokenFromCookies(cookies));
    }
}
