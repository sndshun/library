package com.sndshun.library.service;


import com.sndshun.library.entity.SysPermission;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.vo.MenuPerVo;

import java.util.List;

/**
 * 权限表(SysPermission)表服务接口
 *
 * @author sndshun
 * @since 2022-03-26 17:16:04
 */
public interface SysPermissionService {

    /**
     * 查询所有权限
     * @return
     */
    List<MenuPerVo> getAllIDAndPermission();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysPermission getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPermission 实例对象
     * @return 对象列表
     */
    List<SysPermission> list(SysPermission sysPermission);

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    void page(PageUtil<SysPermission> page);

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    boolean save(SysPermission sysPermission);

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    boolean update(SysPermission sysPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id);

}
