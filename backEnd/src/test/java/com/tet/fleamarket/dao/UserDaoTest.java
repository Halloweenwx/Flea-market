package com.tet.fleamarket.dao;

import com.tet.fleamarket.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

/**
 * @author Hou Weiying
 * @date 2019-11-12 16:15
 */
@SpringBootTest
public class UserDaoTest {
    @Autowired
    public UserDao userDao;
    @Test
    public void add(){
        User user = new User();
        user.setId("big");
        System.out.println(userDao.save(user));
        System.out.println(userDao.findById("big"));
    }
}