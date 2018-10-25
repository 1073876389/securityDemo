package com.xuwenxing.securityDemo.domain.system;

import com.xuwenxing.securityDemo.domain.BaseDao;
import lombok.*;

import java.util.List;

/**
 * 系统菜单
 * Created by xuwx on 2018/10/24.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemMenu  extends BaseDao {
    /**
     * 菜单标题
     */
    private String title ;
    /**
     * 当前菜单显示的内容
     */
    private String info;
    /**
     *菜单对应的页面url
     */
    private String url;
    /**
     * 菜单对应的顺序
     */
    private String sort;
    /**
     * 菜单对应的icon图像
     */
    private String icon;
    /**
     * 当前菜单对应的父级菜单id;
     */
    private Long parentId;
    /**
     * 菜单对应的子菜单集合
     */
    private List<SystemMenu> submenus;
}
