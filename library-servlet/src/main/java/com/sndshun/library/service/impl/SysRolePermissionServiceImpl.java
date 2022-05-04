package com.sndshun.library.service.impl;


import com.github.benmanes.caffeine.cache.Cache;
import com.sndshun.library.common.config.ResultCode;
import com.sndshun.library.common.exception.CommonJsonException;
import com.sndshun.library.dto.UserInfoDTO;
import com.sndshun.library.entity.SysRole;
import com.sndshun.library.entity.SysRolePermission;
import com.sndshun.library.mapper.SysRoleMapper;
import com.sndshun.library.mapper.SysRolePermissionMapper;
import com.sndshun.library.service.SysRolePermissionService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import com.sndshun.library.vo.RoleMenuPerVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限映射表
 * <p>
 * (SysRolePermission)表服务实现类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:06
 */
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private Cache<String, UserInfoDTO> cache;

    /**
     * 查询角色及角色下的权限
     *
     * @return
     */
    @Override
    public List<RoleMenuPerVo> queryRoleIsMenuPer() {
        return sysRolePermissionMapper.queryRoleIsMenuPer();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRolePermission getById(Integer id) {
        return this.sysRolePermissionMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param sysRolePermission 主键
     * @return 实例对象
     */
    @Override
    public List<SysRolePermission> list(SysRolePermission sysRolePermission) {
        return this.sysRolePermissionMapper.queryAll(sysRolePermission);
    }

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<SysRolePermission> page) {
        page.setTotal(this.sysRolePermissionMapper.queryCount());
        List<SysRolePermission> list = this.sysRolePermissionMapper.queryAllByLimit(page);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param sysRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(SysRolePermission sysRolePermission) {
        return this.sysRolePermissionMapper.insert(sysRolePermission) > 0;
    }

    /**
     * 修改数据
     *
     * @param sysRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(SysRolePermission sysRolePermission) {
        return this.sysRolePermissionMapper.update(sysRolePermission) > 0;
    }

    /**
     * 修改角色权限
     *
     * @param permissions
     * @param rid
     * @param name
     * @param uid
     * @return
     */
    @Override
    public boolean updRolePer(List<Integer> permissions, Integer rid, String name, Integer uid) {
        if (permissions.size() == 0 && (null == name || "" == name)) {
            throw new CommonJsonException(Result.error(ResultCode.PARAM_IS_BLANK));
        }
        //删除角色权限
        sysRolePermissionMapper.deleteById(rid);
        SysRole sysRole = new SysRole();
        sysRole.setId(rid);
        sysRole.setDescription(name);
        sysRole.setUpdateBy(uid);
        //修改角色信息
        int rUpd = sysRoleMapper.update(sysRole);
        int PUpd = 0;
        //重新添加角色权限或者不添加
        if (permissions.size() > 0) {
            PUpd = sysRolePermissionMapper.updRolePer(permissions, rid, uid);
        }
        //清除缓存
        cache.invalidateAll();
        return rUpd > 0 && PUpd > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.sysRolePermissionMapper.deleteById(id) > 0;
    }
}
