package com.tet.fleamarket;

import com.tet.fleamarket.dao.UserDao;
import com.tet.fleamarket.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FleamarketApplicationTests {

    @Test
    public void contextLoads() {
    }
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
