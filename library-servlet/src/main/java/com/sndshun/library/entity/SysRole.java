package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色表(SysRole)表实体类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色id
     */

    private Integer id;
    /**
     * 角色
     */

    private String roleName;
    /**
     * 描述
     */

    private String description;
    /**
     * 修改者
     */

    private Integer updateBy;
    /**
     * 创建者
     */

    private Integer createBy;

    private Date updateTime;

    private Date createTime;

}
