package com.sndshun.library.service;

import com.sndshun.library.dto.UserInfoDTO;
import com.sndshun.library.dto.UserLoginDTO;
import com.sndshun.library.entity.SysUser;

/**
 * @Author mr.sun
 * @Creation 2022 2022/3/27 21:18
 * @Desc:
 */
public interface LoginService {

    /**
     * 密码登录
     * @param username
     * @param password
     * @return token
     */
    String passwordLogin(String username, String password);

    /**
     *  获取用户信息
     * @param token
     * @return
     */
    UserInfoDTO getUserInfo(String token);

}
