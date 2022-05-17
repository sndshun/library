package com.sndshun.library.service;


import com.sndshun.library.entity.Credentials;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 借阅凭证表(Credentials)表服务接口
 *
 * @author sndshun
 * @since 2022-05-12 19:15:45
 */
public interface CredentialsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Credentials getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param credentials 实例对象
     * @return 对象列表
     */
    List<Credentials> list(Credentials credentials);

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    void page(PageUtil<Credentials> page);

    /**
     * 新增数据
     *
     * @param credentials 实例对象
     * @return 实例对象
     */
    boolean save(Credentials credentials);

    /**
     * 修改数据
     *
     * @param credentials 实例对象
     * @return 实例对象
     */
    boolean update(Credentials credentials);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(List<Integer> id);

}
