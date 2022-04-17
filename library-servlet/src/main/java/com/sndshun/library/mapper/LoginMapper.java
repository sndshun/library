package com.sndshun.library.mapper;

import com.sndshun.library.dto.UserInfoDTO;
import com.sndshun.library.dto.UserLoginDTO;
import com.sndshun.library.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author mr.sun
 * @Creation 2022 2022/3/26 19:29
 * @Desc:
 */
@Mapper
public interface LoginMapper {

    /**
     * 密码登录
     * @param username
     * @param password
     * @return
     */
    SysUser passwordLogin(String username, String password);


    /**
     *  获取用户信息
     * @param id
     * @return
     */
    UserInfoDTO getUserInfo(Integer id);

}
