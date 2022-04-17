package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色关联表(SysUserRole)表实体类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */

    private Integer id;
    /**
     * 用户id
     */

    private Integer userId;
    /**
     * 角色id
     */

    private Integer roleId;
    /**
     * 更新时间
     */

    private Date updateTime;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 更新者
     */

    private Integer updateBy;
    /**
     * 创建者
     */

    private Integer createBy;

}
