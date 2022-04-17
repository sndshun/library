package com.sndshun.library.vo;

import lombok.Data;

import java.util.Set;

/**
 * @Author mr.sun
 * @Creation 2022 2022/3/30 8:29
 * @Desc:
 */
@Data
public class RoleMenuPerVo {
    private Integer rid;
    private String roleName;
    private String description;
    private Set<MenuPer> menus;
}
@Data
class MenuPer {
    private String menuCode;
    private String menuName;
    private Set<Permission> permissions;
}
@Data
class Permission{
    private Integer pid;
    private String permissionName;
    private Integer requiredPermission;
}



