package com.sndshun.library.service;


import com.sndshun.library.entity.SysRolePermission;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.vo.RoleMenuPerVo;

import java.util.List;

/**
 * 角色权限映射表
 * <p>
 * (SysRolePermission)表服务接口
 *
 * @author sndshun
 * @since 2022-03-26 17:16:06
 */
public interface SysRolePermissionService {

    /**
     * 查询角色及角色下的权限
     * @return
     */
    List<RoleMenuPerVo> queryRoleIsMenuPer();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRolePermission getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRolePermission 实例对象
     * @return 对象列表
     */
    List<SysRolePermission> list(SysRolePermission sysRolePermission);

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    void page(PageUtil<SysRolePermission> page);

    /**
     * 新增数据
     *
     * @param sysRolePermission 实例对象
     * @return 实例对象
     */
    boolean save(SysRolePermission sysRolePermission);

    /**
     * 修改数据
     *
     * @param sysRolePermission 实例对象
     * @return 实例对象
     */
    boolean update(SysRolePermission sysRolePermission);

    /**
     *  修改角色权限
     * @param permissions
     * @param rid
     * @param name
     * @param uid
     * @return
     */
    boolean updRolePer(List<Integer> permissions, Integer rid, String name, Integer uid);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id);

}
