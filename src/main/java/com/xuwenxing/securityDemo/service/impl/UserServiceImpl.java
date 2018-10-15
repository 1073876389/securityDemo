package com.xuwenxing.securityDemo.service.impl;


import com.xuwenxing.securityDemo.dao.UserMapper;
import com.xuwenxing.securityDemo.domain.system.User;
import com.xuwenxing.securityDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuwx on 2018/7/31.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User get(Long id) {
        return userMapper.get(id);
    }

    @Override
    public List<User> findList() {
        List<User> list = userMapper.findList();
        return list;
    }


    @Override
    public User checkUsername(String username) {
        User user = userMapper.checkUsername(username);
        if (user!=null){
        return user;
        }else{
        return null;
        }
    }

    @Override
    public User userLogin(String username, String password) {
        User user =userMapper.userLogin(username,password);
        return user ;
    }

}
