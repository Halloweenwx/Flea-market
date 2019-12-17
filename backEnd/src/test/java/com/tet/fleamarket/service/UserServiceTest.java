package com.tet.fleamarket.service;

import com.tet.fleamarket.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author Hou Weiying
 * @date 2019-12-01 19:14
 */
public class UserServiceTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void usernameExists() {
        userDao.findByUsername("admin2");
    }
}