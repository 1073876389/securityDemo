package com.xuwenxing.securityDemo.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xuwx on 2018/7/31.
 */
@Getter
@Setter
public class BaseDao implements Serializable {
    /**
     * 主键
     */
    private Long id ;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 删除时间
     */
    private Date deleteDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 创建用户ID
     */
    private Long createBy;
    /**
     * 删除用户ID
     */
    private Long deleteBy;
    /**
     * 更新用户ID
     */
    private Long updateBy;

}
