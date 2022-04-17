package com.sndshun.library.service.impl;


import com.sndshun.library.mapper.SysPermissionMapper;
import com.sndshun.library.entity.SysPermission;
import com.sndshun.library.service.SysPermissionService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.vo.MenuPerVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(SysPermission)表服务实现类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:04
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
    @Resource
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 查询所有权限
     *
     * @return
     */
    @Override
    public List<MenuPerVo> getAllIDAndPermission() {
        return sysPermissionMapper.getAllIDAndPermission();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysPermission getById(Integer id) {
        return this.sysPermissionMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param sysPermission 主键
     * @return 实例对象
     */
    @Override
    public List<SysPermission> list(SysPermission sysPermission) {
        return this.sysPermissionMapper.queryAll(sysPermission);
    }

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<SysPermission> page) {
        page.setTotal(this.sysPermissionMapper.queryCount());
        List<SysPermission> list = this.sysPermissionMapper.queryAllByLimit(page);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(SysPermission sysPermission) {
        return this.sysPermissionMapper.insert(sysPermission) > 0;
    }

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(SysPermission sysPermission) {
        return this.sysPermissionMapper.update(sysPermission) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.sysPermissionMapper.deleteById(id) > 0;
    }
}
