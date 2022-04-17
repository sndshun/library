package com.sndshun.library.service;


import com.sndshun.library.entity.SysUser;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author sndshun
 * @since 2022-03-26 17:16:07
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUser 实例对象
     * @return 对象列表
     */
    List<SysUser> list(SysUser sysUser);

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    void page(PageUtil<SysUser> page);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    boolean save(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    boolean update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id);

}
