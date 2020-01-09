package com.tet.fleamarket.service;

import com.tet.fleamarket.dao.CustomerDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Hou Weiying
 * @date 2019-12-01 19:14
 */
public class UserServiceTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void usernameExists() {
        customerDao.findByUsername("admin2");
    }
}