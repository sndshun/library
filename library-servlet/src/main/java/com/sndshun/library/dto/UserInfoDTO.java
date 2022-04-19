package com.sndshun.library.dto;

import com.sndshun.library.entity.SysPermission;
import com.sndshun.library.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author mr.sun
 * @Creation 2022 2022/3/27 21:46
 * @Desc:
 */
@Data
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String phone;
    private Integer sex;
    private String address;
    private String email;
    private String profile;
    private Set<String> menus;
    private Set<String> permissions;
    private Set<String> roles;

}
