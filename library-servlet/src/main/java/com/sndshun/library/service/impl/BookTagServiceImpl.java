package com.sndshun.library.service.impl;


import com.sndshun.library.mapper.BookTagMapper;
import com.sndshun.library.entity.BookTag;
import com.sndshun.library.service.BookTagService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.vo.BookTagVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书籍标签表(BookTag)表服务实现类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:01
 */
@Service("bookTagService")
public class BookTagServiceImpl implements BookTagService {
    @Resource
    private BookTagMapper bookTagMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BookTag getById(Integer id) {
        return this.bookTagMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param bookTag 主键
     * @return 实例对象
     */
    @Override
    public List<BookTag> list(BookTag bookTag) {
        return this.bookTagMapper.queryAll(bookTag);
    }

    /**
     * 分页查询
     * @param bookTag 条件
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<BookTagVo> page, BookTagVo bookTag) {
        page.setTotal(this.bookTagMapper.queryCount(bookTag));
        List<BookTagVo> list = this.bookTagMapper.queryAllByLimit(page,bookTag);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param bookTag 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(BookTag bookTag) {
        return this.bookTagMapper.insert(bookTag) > 0;
    }

    /**
     * 修改数据
     *
     * @param bookTag 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(BookTag bookTag) {
        return this.bookTagMapper.update(bookTag) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.bookTagMapper.deleteById(id) > 0;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        return bookTagMapper.deleteBatch(ids)>0;
    }
}
