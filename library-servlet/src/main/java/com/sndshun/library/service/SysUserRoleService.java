package com.sndshun.library.service;


import com.sndshun.library.entity.SysUserRole;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 用户角色关联表(SysUserRole)表服务接口
 *
 * @author sndshun
 * @since 2022-03-26 17:16:08
 */
public interface SysUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRole getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserRole 实例对象
     * @return 对象列表
     */
    List<SysUserRole> list(SysUserRole sysUserRole);

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    void page(PageUtil<SysUserRole> page);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    boolean save(SysUserRole sysUserRole);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    boolean update(SysUserRole sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id);

}
