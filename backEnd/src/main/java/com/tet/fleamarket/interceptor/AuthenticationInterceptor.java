package com.tet.fleamarket.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.tet.fleamarket.entity.User;
import com.tet.fleamarket.service.TokenService;
import com.tet.fleamarket.service.UserService;
import com.tet.fleamarket.util.Result;
import com.tet.fleamarket.util.Status;
import com.tet.fleamarket.util.TokenRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

import static com.tet.fleamarket.util.status.TokenStatus.*;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        boolean pass = true;
        Status status = TOKEN_INVALID;
        Cookie[] cookies = httpServletRequest.getCookies();
        String token = tokenService.getTokenFromCoolies(cookies);
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        httpServletResponse.setHeader("Content-Type", "application/json; charset=UTF-8");
        PrintWriter out;

        if (!method.isAnnotationPresent(TokenRequired.class)) {
            return true;
        } else {
            if (token == null) {
                status = TOKEN_NOT_FOUND;
                pass = false;
            }
            // 解析token
            String uid = null;
            Map<String, Claim> claims;
            try {
                if (pass) {
                    uid = JWT.decode(token).getAudience().get(0);
                    claims = JWT.decode(token).getClaims();
                }
            } catch (JWTDecodeException e) {
                status = TOKEN_INVALID;
                pass = false;
            }
            User userInBase = null;
            try {
                if (pass && uid != null) {
                    userInBase = userService.getUserByUid(uid);
                }
            } catch (Exception e) {
                status = USER_NOT_FOUND;
                pass = false;
            }
            if (userInBase == null) {
                status = USER_NOT_FOUND;
                pass = false;
            } else {
                // 通过salt验证
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userInBase.getSalt())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    status = TOKEN_INVALID;
                    pass = false;
                }
            }
//            填写返回结果

            if (pass) {
                //刷新cookie
                Cookie cookie = new Cookie("token", tokenService.genUserToken(userInBase));
                httpServletResponse.addCookie(cookie);
            }else{
//              不通过时需要调用writer写回Result
                out = httpServletResponse.getWriter();
                out.append(JSONObject.toJSONString(new Result(status)));
            }
        }
        return pass;

    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
