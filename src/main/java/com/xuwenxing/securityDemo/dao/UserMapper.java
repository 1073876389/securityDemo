package com.xuwenxing.securityDemo.dao;


import com.xuwenxing.securityDemo.domain.system.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *
 * Created by xuwx on 2018/7/31.
 */
@Mapper
public interface UserMapper {
    int save(User user);

    int delete(Long id);

    int update(User user);

    User get(Long id);

    List<User> findList();

    User userLogin(@Param("username") String username, @Param("password") String password);
}
