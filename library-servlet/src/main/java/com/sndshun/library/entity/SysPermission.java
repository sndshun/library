package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限表(SysPermission)表实体类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:04
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 菜单，前端判断是否显示
     */

    private String menuCode;
    /**
     * 菜单释义
     */

    private String menuName;
    /**
     * 权限代码
     */

    private String permissionCode;
    /**
     * 权限释义
     */

    private String permissionName;
    /**
     * 是否本菜单必选权限, 1.必选 2非必选 通常是"列表"权限是必选
     */

    private Integer requiredPermission;
    /**
     * 最后修改时间
     */

    private Date updateTime;
    /**
     * 最后创建时间
     */

    private Date createTime;

}
