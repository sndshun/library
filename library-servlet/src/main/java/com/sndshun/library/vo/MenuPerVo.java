package com.sndshun.library.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author mr.sun
 * @Creation 2022 2022/4/1 20:32
 * @Desc:
 */
@Data
public class MenuPerVo {
    private String menuCode;
    private String menuName;
    private List<Per> permissions;
}

@Data
class Per{
    private Integer id;
    private String permissionCode;
    private String permissionName;
    private Integer requiredPermission;
}
