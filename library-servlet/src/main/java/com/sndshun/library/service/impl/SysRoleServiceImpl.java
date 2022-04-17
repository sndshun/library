package com.sndshun.library.service.impl;


import com.sndshun.library.mapper.SysRoleMapper;
import com.sndshun.library.entity.SysRole;
import com.sndshun.library.service.SysRoleService;
import com.sndshun.library.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色表(SysRole)表服务实现类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:05
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRole getById(Integer id) {
        return this.sysRoleMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param sysRole 主键
     * @return 实例对象
     */
    @Override
    public List<SysRole> list(SysRole sysRole) {
        return this.sysRoleMapper.queryAll(sysRole);
    }

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<SysRole> page) {
        page.setTotal(this.sysRoleMapper.queryCount());
        List<SysRole> list = this.sysRoleMapper.queryAllByLimit(page);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(SysRole sysRole) {
        return this.sysRoleMapper.insert(sysRole) > 0;
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(SysRole sysRole) {
        return this.sysRoleMapper.update(sysRole) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.sysRoleMapper.deleteById(id) > 0;
    }
}
