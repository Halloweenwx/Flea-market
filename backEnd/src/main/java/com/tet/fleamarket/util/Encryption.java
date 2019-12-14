package com.tet.fleamarket.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Hou Weiying
 * @date 2019-12-01 17:40
 */
public class Encryption {
    static public String getMD5(String plainText) {
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
    static public String randomString(int len){
        return RandomStringUtils.randomAscii(len);
    }
    static public String randomNumber(int len){
        return RandomStringUtils.randomNumeric(len);
    }
}
