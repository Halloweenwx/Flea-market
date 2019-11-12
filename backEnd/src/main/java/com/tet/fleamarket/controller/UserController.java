package com.tet.fleamarket.controller;

import com.tet.fleamarket.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/login")
    public ResponseEntity login(){
        System.out.println(ResponseEntity.ok("yoo"));
        return ResponseEntity.ok("yoo");
    }
    @GetMapping("/list")
    public ResponseEntity list(){
        System.out.println(userDao.findById("hi"));
        return ResponseEntity.ok(userDao.findById("hi"));
    }
}
