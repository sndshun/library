package com.sndshun.library.service;


import com.sndshun.library.entity.BookTags;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * (BookTags)表服务接口
 *
 * @author sndshun
 * @since 2022-04-08 16:42:44
 */
public interface BookTagsService {

    /**
     * 热门标签
     * @param count 数量
     * @return 标签列表
     */
    List<BookTags> hotTags(Integer count);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BookTags getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param bookTags 实例对象
     * @return 对象列表
     */
    List<BookTags> list(BookTags bookTags);

    /**
     * 分页查询
     * @param page 分页工具类
     */
    void page(PageUtil<BookTags> page);

    /**
     * 新增数据
     *
     * @param bookTags 实例对象
     * @return 实例对象
     */
    boolean save(BookTags bookTags);

    /**
     * 修改数据
     *
     * @param bookTags 实例对象
     * @return 实例对象
     */
    boolean update(BookTags bookTags);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id);

}
