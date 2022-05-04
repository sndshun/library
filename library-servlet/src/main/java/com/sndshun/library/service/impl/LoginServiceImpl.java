package com.sndshun.library.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.sndshun.library.common.config.ResultCode;
import com.sndshun.library.common.exception.CommonJsonException;
import com.sndshun.library.dto.UserInfoDTO;
import com.sndshun.library.dto.UserLoginDTO;
import com.sndshun.library.entity.SysUser;
import com.sndshun.library.entity.SysUserAuths;
import com.sndshun.library.entity.SysUserRole;
import com.sndshun.library.mapper.*;
import com.sndshun.library.service.LoginService;
import com.sndshun.library.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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
    private SysUserMapper userMapper;
    @Resource
    private SysUserRoleMapper userRoleMapper;
    @Resource
    private SysUserAuthsMapper userAuthsMapper;

    @Resource
    private Cache<String, UserInfoDTO> cache;

    @Resource Cache<String, String> codeCache;

    private final String EMAIL = "EMAIL";
    private final String ID = "ID";

    private final int DEFAULT_ROLE_ID = 2;

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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(UserLoginDTO userLoginDTO) {
        String code= codeCache.getIfPresent(userLoginDTO.getEmail());
        log.debug("验证码====================>" + code);
        log.debug("邮箱====================>" + userLoginDTO.getEmail());
        log.debug("验证码====================>" + userLoginDTO.getCode());
        if(null==code){
            throw new CommonJsonException(Result.error(ResultCode.REG_EMAIL_CODE_OUT));
        }else if(!userLoginDTO.getCode().equals(code)){
            throw new CommonJsonException(Result.error(ResultCode.REG_EMAIL_CODE_INVOKE));
        }
        SysUser sysUser = new SysUser();
        sysUser.setName(userLoginDTO.getName());
        sysUser.setEmail(userLoginDTO.getEmail());
        int user= this.userMapper.insert(sysUser);

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getId());
        sysUserRole.setRoleId(DEFAULT_ROLE_ID);
         this.userRoleMapper.insert(sysUserRole);


        List<SysUserAuths> userAuthsList = new ArrayList<>();
        int auth= 0;
        try {
            SysUserAuths emailAuths = new SysUserAuths();
            emailAuths.setUserId(sysUser.getId());
            emailAuths.setIdentityType(EMAIL);
            emailAuths.setIdentifier(userLoginDTO.getEmail());
            emailAuths.setCredential(userLoginDTO.getPassword());

            SysUserAuths idAuths = new SysUserAuths();
            idAuths.setUserId(sysUser.getId());
            idAuths.setIdentityType(ID);
            idAuths.setIdentifier(userLoginDTO.getUsername());
            idAuths.setCredential(userLoginDTO.getPassword());

            userAuthsList.add(emailAuths);
            userAuthsList.add(idAuths);
            auth = this.userAuthsMapper.insertBatch(userAuthsList);
        }catch (DuplicateKeyException e){
            throw new CommonJsonException(Result.error(ResultCode.USER_HAS_EXISTED));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return auth>0&&user>0;
    }


}
