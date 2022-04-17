package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色权限映射表
 * <p>
 * (SysRolePermission)表实体类
 *
 * @author sndshun
 * @since 2022-04-03 00:28:53
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色id
     */

    private Integer roleId;
    /**
     * 权限id
     */

    private Integer permsId;
    /**
     * 更新者
     */

    private Integer updateBy;
    /**
     * 创建者
     */

    private Integer createBy;
    /**
     * 更新时间
     */

    private Date updateTime;
    /**
     * 创建时间
     */

    private Date createTime;

}
