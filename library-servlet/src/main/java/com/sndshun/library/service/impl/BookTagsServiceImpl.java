package com.sndshun.library.service.impl;


import com.sndshun.library.mapper.BookTagsMapper;
import com.sndshun.library.entity.BookTags;
import com.sndshun.library.service.BookTagsService;
import com.sndshun.library.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BookTags)表服务实现类
 *
 * @author sndshun
 * @since 2022-04-08 16:42:44
 */
@Service("bookTagsService")
public class BookTagsServiceImpl implements BookTagsService {
    @Resource
    private BookTagsMapper bookTagsMapper;

    /**
     * 热门标签
     *
     * @param count 数量
     * @return 标签列表
     */
    @Override
    public List<BookTags> hotTags(Integer count) {
        return this.bookTagsMapper.hotTags(count);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BookTags getById(Integer id) {
        return this.bookTagsMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param bookTags 主键
     * @return 实例对象
     */
    @Override
    public List<BookTags> list(BookTags bookTags) {
        return this.bookTagsMapper.queryAll(bookTags);
    }

     /**
     * 分页查询
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<BookTags> page) {
        page.setTotal(this.bookTagsMapper.queryCount());
        List<BookTags> list = this.bookTagsMapper.queryAllByLimit(page);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param bookTags 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(BookTags bookTags) {
        return this.bookTagsMapper.insert(bookTags)>0;
    }

    /**
     * 修改数据
     *
     * @param bookTags 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(BookTags bookTags) {
        return this.bookTagsMapper.update(bookTags)>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.bookTagsMapper.deleteById(id)>0;
    }
}
