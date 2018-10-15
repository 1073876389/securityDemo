package com.xuwenxing.securityDemo.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BCrypt加密方法测试类
 * Created by xuwx on 2018/10/11.
 */
public class TestEncoder{

    public static void main(String[] args) {

        String password= "1024";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        String encodePassword = encoder.encode(password);
        System.out.println(encodePassword);
    }

    public void encoder() throws  Exception{


    }

}
