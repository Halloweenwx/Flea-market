package com.tet.fleamarket;

import com.tet.fleamarket.dao.UserDao;
import com.tet.fleamarket.entity.Administrator;
import com.tet.fleamarket.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.lang3.RandomStringUtils;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FleamarketApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Autowired
    public UserDao userDao;
    @Test
    public void addAdmin(){
        User admin = new Administrator();
        String salt = RandomStringUtils.randomAscii(10);
        admin.setSalt(salt);

        userDao.save(admin);
        System.out.println(admin);
    }
}
