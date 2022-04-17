package com.sndshun.library.service.impl;


import com.sndshun.library.mapper.SysUserRoleMapper;
import com.sndshun.library.entity.SysUserRole;
import com.sndshun.library.service.SysUserRoleService;
import com.sndshun.library.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色关联表(SysUserRole)表服务实现类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:08
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserRole getById(Integer id) {
        return this.sysUserRoleMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param sysUserRole 主键
     * @return 实例对象
     */
    @Override
    public List<SysUserRole> list(SysUserRole sysUserRole) {
        return this.sysUserRoleMapper.queryAll(sysUserRole);
    }

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<SysUserRole> page) {
        page.setTotal(this.sysUserRoleMapper.queryCount());
        List<SysUserRole> list = this.sysUserRoleMapper.queryAllByLimit(page);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(SysUserRole sysUserRole) {
        return this.sysUserRoleMapper.insert(sysUserRole) > 0;
    }

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(SysUserRole sysUserRole) {
        return this.sysUserRoleMapper.update(sysUserRole) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.sysUserRoleMapper.deleteById(id) > 0;
    }
}
