package com.sndshun.library.service.impl;


import com.sndshun.library.mapper.CredentialsMapper;
import com.sndshun.library.entity.Credentials;
import com.sndshun.library.service.CredentialsService;
import com.sndshun.library.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 借阅凭证表(Credentials)表服务实现类
 *
 * @author sndshun
 * @since 2022-05-12 19:15:45
 */
@Service("credentialsService")
public class CredentialsServiceImpl implements CredentialsService {
    @Resource
    private CredentialsMapper credentialsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Credentials getById(Integer id) {
        return this.credentialsMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param credentials 主键
     * @return 实例对象
     */
    @Override
    public List<Credentials> list(Credentials credentials) {
        return this.credentialsMapper.queryAll(credentials);
    }

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<Credentials> page) {
        page.setTotal(this.credentialsMapper.queryCount());
        List<Credentials> list = this.credentialsMapper.queryAllByLimit(page);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param credentials 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(Credentials credentials) {
        return this.credentialsMapper.insert(credentials) > 0;
    }

    /**
     * 修改数据
     *
     * @param credentials 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Credentials credentials) {
        return this.credentialsMapper.update(credentials) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(List<Integer> id) {
        if (id.size() > 1) {
            return this.credentialsMapper.deleteBatch(id) > 0;
        } else {
            return this.credentialsMapper.deleteById(id.get(0)) > 0;
        }
    }
}
