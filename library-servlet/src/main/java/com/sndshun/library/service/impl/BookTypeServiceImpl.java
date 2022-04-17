package com.sndshun.library.service.impl;


import com.sndshun.library.mapper.BookTypeMapper;
import com.sndshun.library.entity.BookType;
import com.sndshun.library.service.BookTypeService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.vo.BookTypeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书籍类型表(BookType)表服务实现类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:02
 */
@Service("bookTypeService")
public class BookTypeServiceImpl implements BookTypeService {
    @Resource
    private BookTypeMapper bookTypeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BookType getById(Integer id) {
        return this.bookTypeMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param bookType 主键
     * @return 实例对象
     */
    @Override
    public List<BookType> list(BookType bookType) {
        return this.bookTypeMapper.queryAll(bookType);
    }

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<BookTypeVo> page, BookTypeVo vo) {
        page.setTotal(this.bookTypeMapper.queryCount(vo));
        List<BookTypeVo> list = this.bookTypeMapper.queryAllByLimit(page,vo);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param bookType 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(BookType bookType) {
        return this.bookTypeMapper.insert(bookType) > 0;
    }

    /**
     * 修改数据
     *
     * @param bookType 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(BookType bookType) {
        return this.bookTypeMapper.update(bookType) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.bookTypeMapper.deleteById(id) > 0;
    }

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    @Override
    public boolean deleteBatch(List<Integer> list) {
        return bookTypeMapper.deleteBatch(list)>0;
    }
}
