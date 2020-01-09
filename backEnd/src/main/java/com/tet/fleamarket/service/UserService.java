package com.tet.fleamarket.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.tet.fleamarket.dao.CartDao;
import com.tet.fleamarket.dao.CustomerDao;
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
    private CustomerDao customerDao;

    @Autowired
    private CartDao cartDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("${values.service.UserService.saltLen}")
    private int saltLen;

    public Customer getCustomerByUsername(String username) {
        if (!username.isEmpty()) {
            return customerDao.findByUsername(username);
        } else {
            return new Customer();
        }
    }

    public Customer getCustomerByUid(String uid) {
        if (!uid.isEmpty()) {
            System.out.println(customerDao.findByUid(uid));
            return customerDao.findByUid(uid);
        } else {
            return new Customer();
        }
    }

    public Boolean usernameExists(String username) {
        if (username.isEmpty()) {
            return false;
        }
        Customer customerInDataBase = getCustomerByUsername(username);
        if (userIsLegal(customerInDataBase)) {
            logger.info(customerInDataBase.getUsername());
        }
        return userIsLegal(customerInDataBase);
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
        User userInBase = getCustomerByUsername(userToCheck.getUsername());
        if (userIsLegal(userInBase)) {
            String keyToCheck = userToCheck.getPassword() + userInBase.getSalt();
            String md5 = getMD5(keyToCheck);
            if (md5 != null) {
                return md5.equals(userInBase.getPassword());
            }
        }
        return false;
    }

    public String addCustomerAndCart(Customer customerToAdd) {
        customerToAdd.setSalt(randomString(saltLen));
        customerToAdd.setPassword(getMD5(customerToAdd.getPassword() + customerToAdd.getSalt()));
        Customer customer = new Customer(customerDao.save(customerToAdd));
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartDao.save(cart);
        return customer.getUid();
    }

    public Boolean update(User updateInfo) {
        Boolean updated = false;
        Customer userToUpdate = getCustomerByUid(updateInfo.getUid());
        try {
            if (updateInfo.getPhone() != null) {
                userToUpdate.setPhone(updateInfo.getPhone());
                updated = true;
            }
            if (updateInfo.getPassword() != null) {
                userToUpdate.setSalt(randomString(saltLen));
                userToUpdate.setPassword(getMD5(updateInfo.getPassword() + userToUpdate.getSalt()));
                updated = true;
            }
            if(updated) {
                customerDao.save(userToUpdate);
            }
        } catch (Exception e) {
            logger.info("update error" + updateInfo.toString());
            updated = false;
        }
        return updated;
    }
}
