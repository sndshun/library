package com.sndshun.library.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.sndshun.library.common.config.ResultCode;
import com.sndshun.library.common.exception.CommonJsonException;
import com.sndshun.library.dto.UserInfoDTO;
import com.sndshun.library.entity.SysUser;
import com.sndshun.library.mapper.LoginMapper;
import com.sndshun.library.mapper.SysPermissionMapper;
import com.sndshun.library.service.LoginService;
import com.sndshun.library.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author mr.sun
 * @Creation 2022 2022/3/27 21:18
 * @Desc:
 */
@Slf4j
@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService {

    private final Integer rootUser = 1;

    @Resource
    private LoginMapper loginMapper;
    @Resource
    private SysPermissionMapper permissionMapper;

    @Resource
    private Cache<String, UserInfoDTO> cache;

    /**
     * 密码登录
     *
     * @param username
     * @param password
     * @return token
     */
    @Override
    public String passwordLogin(String username, String password) {
        SysUser sysUser = loginMapper.passwordLogin(username, password);
        if (null == sysUser) {
            throw new CommonJsonException(Result.error(ResultCode.USER_LOGIN_ERROR));
        }
        StpUtil.login(sysUser.getId());
        String token = StpUtil.getTokenValue();
        return token;
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    @Override
    public UserInfoDTO getUserInfo(String token) {
        log.debug("登录token====================>" + token);
        if (null == StpUtil.getLoginIdByToken(token)) {
            throw new CommonJsonException(Result.error(ResultCode.USER_LOGIN_OUT));
        }

        Integer id = StpUtil.getLoginIdAsInt();
        log.debug("解析token===>id====================>" + id);
        if (null == id) {
            throw new CommonJsonException(Result.error(ResultCode.USER_LOGIN_ERROR));
        }

        UserInfoDTO userInfoDTO = cache.getIfPresent(token);

        if (null != userInfoDTO) {
            log.debug("从缓存中返回用户信息===========================》" + userInfoDTO);
            return userInfoDTO;
        }


        userInfoDTO = loginMapper.getUserInfo(id);

        if (userInfoDTO.getId().equals(rootUser)) {
            userInfoDTO.setMenus(permissionMapper.getAllMenus());
            userInfoDTO.setPermissions(permissionMapper.getAllPermission());
        }


        log.debug("用户信息数据库查询====================>" + userInfoDTO);
        if (null == userInfoDTO) {
            throw new CommonJsonException(Result.error(ResultCode.ERROR));
        }
        cache.put(token, userInfoDTO);
        return userInfoDTO;
    }


}
