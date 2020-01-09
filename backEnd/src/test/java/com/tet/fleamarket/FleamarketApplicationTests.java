package com.tet.fleamarket;

import com.tet.fleamarket.dao.CustomerDao;
import com.tet.fleamarket.entity.Administrator;
import com.tet.fleamarket.entity.User;
import com.tet.fleamarket.service.UserService;
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
    public CustomerDao customerDao;
    @Autowired
    public UserService userService;
    @Test
    public void addAdmin(){
        User admin = new Administrator();
        admin = customerDao.findByUsername("admin2");
        System.out.println(userService.usernameExists("admin2"));
        System.out.println(admin.getUsername());
    }
}
