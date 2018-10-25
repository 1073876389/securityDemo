package com.xuwenxing.securityDemo.service;


import com.xuwenxing.securityDemo.domain.system.SystemMenu;

import java.util.List;

/**
 * Created by xuwx on 2018/7/31.
 */
public interface SystemMenuService {

    void save(SystemMenu systemMenu);

    void delete(Long id);

    void update(SystemMenu systemMenu);

    SystemMenu get(Long id);

    List<SystemMenu> findList();

}
