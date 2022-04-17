package com.sndshun.library.service.impl;


import com.sndshun.library.mapper.SysUserMapper;
import com.sndshun.library.entity.SysUser;
import com.sndshun.library.service.SysUserService;
import com.sndshun.library.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:07
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser getById(Integer id) {
        return this.sysUserMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param sysUser 主键
     * @return 实例对象
     */
    @Override
    public List<SysUser> list(SysUser sysUser) {
        return this.sysUserMapper.queryAll(sysUser);
    }

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<SysUser> page) {
        page.setTotal(this.sysUserMapper.queryCount());
        List<SysUser> list = this.sysUserMapper.queryAllByLimit(page);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(SysUser sysUser) {
        return this.sysUserMapper.insert(sysUser) > 0;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(SysUser sysUser) {
        return this.sysUserMapper.update(sysUser) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.sysUserMapper.deleteById(id) > 0;
    }
}
