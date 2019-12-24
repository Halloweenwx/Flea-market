package com.tet.fleamarket.service;

import com.tet.fleamarket.dao.CartDao;
import com.tet.fleamarket.dao.UserDao;
import com.tet.fleamarket.entity.Cart;
import com.tet.fleamarket.entity.Customer;
import com.tet.fleamarket.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import static com.tet.fleamarket.util.Encryption.getMD5;
import static com.tet.fleamarket.util.Encryption.randomString;

/**
 * @author Hou Weiying
 * @date 2019-12-01 17:26
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("${values.service.UserService.saltLen}")
    private int saltLen;

    public User getUserByUsername(String username) {
        if (!username.isEmpty()) {
            return userDao.findByUsername(username);
        } else {
            return new User();
        }
    }

    public User getUserByUid(String uid) {
        if (!uid.isEmpty()) {
            System.out.println(userDao.findByUid(uid));
            return userDao.findByUid(uid);
        } else {
            return new User();
        }
    }

    public Boolean usernameExists(String username) {
        if (username.isEmpty()) {
            return false;
        }
        User userInDataBase = getUserByUsername(username);
        if (userIsLegal(userInDataBase)) {
            logger.info(userInDataBase.getUsername());
        }
        return userIsLegal(userInDataBase);
    }

    public Boolean userIsLegal(User user) {
        if (user != null && user.getUsername() != null && user.getPhone() != null) {
            return !user.getUsername().isEmpty() && user.getPhone().length() <= 11;
        } else {
            logger.info("用户不存在");
            return false;
        }
    }

    public Boolean checkPassword(User userToCheck) {
        User userInBase = getUserByUsername(userToCheck.getUsername());
        if (userIsLegal(userInBase)) {
            String keyToCheck = userToCheck.getPassword() + userInBase.getSalt();
            String md5 = getMD5(keyToCheck);
            if (md5 != null) {
                return md5.equals(userInBase.getPassword());
            }
        }
        return false;
    }

    public String addCustomerAndCart(User userToAdd) {
        userToAdd.setSalt(randomString(saltLen));
        userToAdd.setPassword(getMD5(userToAdd.getPassword() + userToAdd.getSalt()));
        Customer customer = new Customer(userDao.save(userToAdd));
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartDao.save(cart);
        return customer.getUid();
    }

    public Boolean update(User updateInfo) {
        boolean updated = false;
        User userToUpdate = getUserByUid(updateInfo.getUid());
        if (!updateInfo.getUsername().equals(userToUpdate.getUsername())) {
            userToUpdate.setUsername(updateInfo.getUsername());
            updated = true;
        } else if (updateInfo.getPhone() != null && !userToUpdate.getPhone().equals(updateInfo.getPhone())) {
            userToUpdate.setPhone(updateInfo.getPhone());
            updated = true;
        } else if (updateInfo.getCity() != null && userToUpdate.getCity() != updateInfo.getCity()) {
            userToUpdate.setCity(updateInfo.getCity());
        }
        userDao.save(userToUpdate);
        return updated;
    }
}
