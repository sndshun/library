package com.sndshun.library.service;


import com.sndshun.library.entity.SysRole;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 用户角色表(SysRole)表服务接口
 *
 * @author sndshun
 * @since 2022-03-26 17:16:05
 */
public interface SysRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRole getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRole 实例对象
     * @return 对象列表
     */
    List<SysRole> list(SysRole sysRole);

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    void page(PageUtil<SysRole> page);

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    boolean save(SysRole sysRole);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    boolean update(SysRole sysRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id);

}
