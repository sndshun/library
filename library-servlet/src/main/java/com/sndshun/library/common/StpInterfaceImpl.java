package com.sndshun.library.common;

import java.util.ArrayList;
import java.util.List;

import cn.dev33.satoken.stp.StpUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.sndshun.library.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import cn.dev33.satoken.stp.StpInterface;

import javax.annotation.Resource;


/**
 * 自定义权限验证接口扩展
 * @author mr.sun
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
@Slf4j
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private Cache<String, UserInfoDTO> cache;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.debug("赋予用户权限===================》"+loginId);
        log.debug("赋予用户权限===================》"+loginType);

        String token = StpUtil.getTokenValueByLoginId(loginId);
        log.debug("解析token======================》"+token);
        UserInfoDTO userInfo = cache.getIfPresent(token);

        log.debug("用户拥有权限==================》"+userInfo.getPermissions());

        return new ArrayList<>(userInfo.getPermissions());
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        log.debug("赋予用户角色===================》"+loginId);
        log.debug("赋予用户角色===================》"+loginType);

        String token = StpUtil.getTokenValueByLoginId(loginId);
        log.debug("解析token======================》"+token);
        UserInfoDTO userInfo = cache.getIfPresent(token);
        log.debug("用户拥有角色==================》"+userInfo.getRoles());

        return new ArrayList<>(userInfo.getRoles());
    }

}
