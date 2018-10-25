package com.xuwenxing.securityDemo.dao;

import com.xuwenxing.securityDemo.domain.system.SystemMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xuwx on 2018/10/24.
 */
@Mapper
public interface SystemMenuMapper {

    int save(SystemMenu systemMenu);

    int delete(Long id);

    int update(SystemMenu systemMenu);

    SystemMenu get(Long id);

    List<SystemMenu> findList();

}
