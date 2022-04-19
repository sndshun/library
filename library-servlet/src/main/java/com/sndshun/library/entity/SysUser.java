package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息表(SysUser)表实体类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */

    private Integer id;
    /**
     * 用户昵称
     */

    private String name;
    /**
     * 联系电话
     */

    private String phone;
    /**
     * 用户性别(0：未知，1：男，2：女)
     */

    private Integer sex;
    /**
     * 联系地址
     */

    private String address;
    /**
     * 邮箱
     */

    private String email;
    /**
     * 头像
     */
    private String profile;
    /**
     * 更新时间
     */

    private Date updateTime;
    /**
     * 创建时间
     */

    private Date createTime;

}
