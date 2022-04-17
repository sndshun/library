package com.sndshun.library.service.impl;


import com.sndshun.library.mapper.SysUserAuthsMapper;
import com.sndshun.library.entity.SysUserAuths;
import com.sndshun.library.service.SysUserAuthsService;
import com.sndshun.library.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户授权表(SysUserAuths)表服务实现类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:08
 */
@Service("sysUserAuthsService")
public class SysUserAuthsServiceImpl implements SysUserAuthsService {
    @Resource
    private SysUserAuthsMapper sysUserAuthsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserAuths getById(Integer id) {
        return this.sysUserAuthsMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param sysUserAuths 主键
     * @return 实例对象
     */
    @Override
    public List<SysUserAuths> list(SysUserAuths sysUserAuths) {
        return this.sysUserAuthsMapper.queryAll(sysUserAuths);
    }

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<SysUserAuths> page) {
        page.setTotal(this.sysUserAuthsMapper.queryCount());
        List<SysUserAuths> list = this.sysUserAuthsMapper.queryAllByLimit(page);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param sysUserAuths 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(SysUserAuths sysUserAuths) {
        return this.sysUserAuthsMapper.insert(sysUserAuths) > 0;
    }

    /**
     * 修改数据
     *
     * @param sysUserAuths 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(SysUserAuths sysUserAuths) {
        return this.sysUserAuthsMapper.update(sysUserAuths) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.sysUserAuthsMapper.deleteById(id) > 0;
    }
}
