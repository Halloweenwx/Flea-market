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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        boolean pass = true;
        Status status = TOKEN_VALID;
//        Cookie[] cookies = httpServletRequest.getCookies();
//        String token = tokenService.getTokenFromCookies(cookies);
        String token = httpServletRequest.getHeader("Authorization");
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
                logger.info(status.getMsg());
            }
            // 解析token
            String uid = null;
            String username = null;
            Map<String, Claim> claims;
            try {
                if (pass) {
                    uid = JWT.decode(token).getAudience().get(0);
                    claims = JWT.decode(token).getClaims();
                    username = claims.get("username").asString();
                }
            } catch (JWTDecodeException e) {
                status = TOKEN_INVALID;
                pass = false;
                logger.info(status.getMsg());
            }
            User userInBase = null;
            try {
                if (pass && uid != null) {
                    userInBase = userService.getCustomerByUid(uid);
                    if (!userService.userIsLegal(userInBase)) {
                        status = USER_NOT_FOUND;
                        pass = false;
                        logger.info(status.getMsg());
                    } else {
                        // 通过salt验证
                        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userInBase.getSalt())).build();
                        try {
                            jwtVerifier.verify(token);
                        } catch (JWTVerificationException e) {
                            status = TOKEN_INVALID;
                            pass = false;
                            logger.info(status.getMsg());
                        }
                    }
                }
            } catch (Exception e) {
                status = USER_NOT_FOUND;
                pass = false;
                logger.info(status.getMsg());
            }

//            填写返回结果

            if (pass) {
                //刷新cookie
//                Cookie cookie = new Cookie("token", tokenService.genUserToken(userInBase));
//                httpServletResponse.addCookie(cookie);
//                httpServletRequest.setAttribute("tuid", uid);
                logger.info(status.getMsg() + ":当前用户为" + username);
            } else {
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
