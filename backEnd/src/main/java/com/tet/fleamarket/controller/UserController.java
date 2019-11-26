package com.tet.fleamarket.controller;

import com.tet.fleamarket.dao.IdleItemDao;
import com.tet.fleamarket.dao.ItemDao;
import com.tet.fleamarket.dao.UserDao;
import com.tet.fleamarket.entity.IdleItem;
import com.tet.fleamarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */
@RestController
public class UserController {
    private final UserDao userDao;
    private final ItemDao itemDao;
    private final IdleItemDao idleItemDao;

    @Autowired
    public UserController(UserDao userDao, ItemDao itemDao, IdleItemDao idleItemDao) {
        this.userDao = userDao;
        this.itemDao = itemDao;
        this.idleItemDao = idleItemDao;
    }

    @PostMapping("/login")
    public ResponseEntity<JSONObject> login(@RequestBody() User loginUser) {
        JSONObject data = new JSONObject();
        User userInBase = userDao.findByUsername(loginUser.getUsername());

        try {
            if (userInBase == null) {
                //用户不存在
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (!checkPassword(loginUser, userInBase)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            } else {
                //登录成功

                return ResponseEntity.ok(data);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<JSONObject> register(@RequestBody() User registerUser){
        JSONObject data = new JSONObject();
        if()
    }
    @GetMapping("/list")
    public ResponseEntity list() {
        System.out.println(userDao.findById("hi"));
        return ResponseEntity.ok(userDao.findById("hi"));
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        IdleItem item = new IdleItem();
        item.setStartPrice(100d);
        item.setPriceFixed(false);

        itemDao.save(item);

        return ResponseEntity.ok(userDao.findById("hi"));
    }

    private Boolean checkPassword(User userToCheck, User userInBase) {

        String keyToCheck = userToCheck.getPassword() + userInBase.getSalt();
        String md5 = getMD5(keyToCheck);
        System.out.println(keyToCheck);
        System.out.println(md5);
        return md5.equals(userInBase.getPassword());
    }

    private static String getMD5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //获取MD5实例
            md.update(plainText.getBytes());
            //此处传入要加密的byte类型值
            byte[] digest = md.digest();
            //此处得到的是md5加密后的byte类型值

            int i;
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                i = b;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(i));
                //通过Integer.toHexString方法把值变为16进制
            }
            return sb.toString().substring(0, 32);
            //从下标0开始，length目的是截取多少长度的值
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
