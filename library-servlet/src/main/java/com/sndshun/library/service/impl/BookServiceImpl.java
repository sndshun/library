package com.sndshun.library.service.impl;


import com.sndshun.library.entity.Book;
import com.sndshun.library.mapper.BookMapper;
import com.sndshun.library.mapper.BookTagsMapper;
import com.sndshun.library.service.BookService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.vo.BookVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书籍信息表(Book)表服务实现类
 *
 * @author sndshun
 * @since 2022-03-26 17:15:58
 */
@Slf4j
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;

    @Resource
    private BookTagsMapper tagsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BookVo getById(Integer id) {
        return this.bookMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param book 主键
     * @return 实例对象
     */
    @Override
    public List<Book> list(Book book) {
        return this.bookMapper.queryAll(book);
    }

    /**
     * 分页查询
     * @param bookVo 分页条件
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<BookVo> page, BookVo bookVo) {
        page.setTotal(this.bookMapper.queryCount(bookVo));
        List<BookVo> list = this.bookMapper.queryAllByLimit(page,bookVo);
        page.setList(list);
    }

    /**
     * 前台使用
     *
     * @param page
     * @param bookVo
     */
    @Override
    public void queryBookList(PageUtil<BookVo> page, BookVo bookVo) {
        page.setTotal(this.bookMapper.queryCount(bookVo));
        List<BookVo> list = this.bookMapper.queryBookList(page,bookVo);
        page.setList(list);
    }

    /**
     * 查询图书标签
     *
     * @param id
     * @return
     */
    @Override
    public BookVo queryBookTags(Integer id) {
        return this.bookMapper.queryBookTags(id);
    }

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(Book book) {
        return this.bookMapper.insert(book) > 0;
    }

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Book book) {
        return this.bookMapper.update(book) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(List<Integer> id) {
        log.debug("delete bookCount: {}", id.size());
        int bookCount=0;
        int tabsCount=0;
        //判断是否为批量删除
        if (id.size() > 1) {
            log.debug("delete book: {}", id);
            //批量删除
            bookCount= bookMapper.deleteBatch(id);
            //批量删除书籍标签
            tabsCount= tagsMapper.deleteBatchByBookId(id);
        } else {
            log.debug("delete book: {}", id.get(0));
            // 删除书籍
            bookCount=this.bookMapper.deleteById(id.get(0));
            // 删除书籍标签
            tabsCount = this.tagsMapper.deleteByBookId(id.get(0));
        }
        return (bookCount+tabsCount) > 0;
    }
}
