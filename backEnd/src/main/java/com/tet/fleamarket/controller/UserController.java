package com.tet.fleamarket.controller;

import com.tet.fleamarket.entity.Administrator;
import com.tet.fleamarket.entity.Customer;
import com.tet.fleamarket.entity.User;
import com.tet.fleamarket.service.TokenService;
import com.tet.fleamarket.service.UserService;
import com.tet.fleamarket.util.Result;
import com.tet.fleamarket.util.Status;
import com.tet.fleamarket.util.TokenRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.tet.fleamarket.util.Encryption.randomNumber;
import static com.tet.fleamarket.util.status.FetchStatus.FATCH_SUCCESS;
import static com.tet.fleamarket.util.status.UserStatus.*;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/login")
    public Result login(@RequestBody() User loginUser, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Status status = BAD_REQUEST;
        System.out.println(loginUser.getUsername() + ":" + loginUser.getPassword());

        if (loginUser.getIsCustomer()) {
            loginUser = new Customer(loginUser);
        } else {
            loginUser = new Administrator(loginUser);
        }
        try {
            if (!userService.usernameExists(loginUser.getUsername())) {
                //用户名不存在
                status = USER_NOT_FOUND;
            } else if (!userService.checkPassword(loginUser)) {
                //密码不正确
                status = WRONG_PASSWORD;
            } else {
                //登录成功
                status = LOGIN_SUCCESS;
                User userInBase = userService.getUserByUsername(loginUser.getUsername());
                String token = tokenService.genUserToken(userInBase);
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                cookie.setMaxAge(3600);
                response.addCookie(cookie);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("未知错误");
        }
        logger.info(loginUser.getUsername() + status.toString());

        return new Result(status, data);
    }

    @PostMapping("/register/info")
    public Result register(@RequestBody() Customer registerCustomer) {
        JSONObject data = new JSONObject();
        Status status = BAD_REQUEST;
        if (userService.usernameExists(registerCustomer.getUsername())) {
            //用户存在
            status = ALREADY_EXISTS;
        } else {
            //注册成功
            status = REGISTER_SUCCESS;
            userService.addUser(registerCustomer);
        }
        logger.info(registerCustomer.getUsername() + status.toString());
        return new Result(status, data);
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register/phone")
    public Result register(@RequestBody() JSONObject res) {
        JSONObject data = new JSONObject();
        Status status = BAD_REQUEST;
        System.out.println(res.get("phone"));
        try {
            String checkNum = randomNumber(6);
            data.put("checkNum", checkNum);
            status = SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("未知错误");
        }
        return new Result(status, data);
    }

    @TokenRequired
    @GetMapping("/home/info")
    public Result userInfo(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        String token = tokenService.getTokenFromCoolies(cookies);
        User user = tokenService.getUserFromToken(token);
        Status status;
        User userInBase = userService.getUserByUid(user.getUid());
        status = FATCH_SUCCESS;
        return new Result(status, userInBase);
    }
//    @GetMapping("/list")
//    public ResponseEntity list() {
//        System.out.println(userDao.findById("hi"));
//        return ResponseEntity.ok(userDao.findById("hi"));
//    }
//
//    @GetMapping("/test")
//    public ResponseEntity test() {
//        IdleItem item = new IdleItem();
//        item.setStartPrice(100d);
//        item.setPriceFixed(false);
//
//        itemDao.save(item);
//
//        return ResponseEntity.ok(userDao.findById("hi"));
//    }


}
