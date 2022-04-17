package com.sndshun.library.service;


import com.sndshun.library.entity.Book;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.vo.BookVo;

import java.util.List;

/**
 * 书籍信息表(Book)表服务接口
 *
 * @author sndshun
 * @since 2022-03-26 17:15:58
 */
public interface BookService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BookVo getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param book 实例对象
     * @return 对象列表
     */
    List<Book> list(Book book);

    /**
     * 分页查询
     * @param bookVo 查询条件
     * @param page 分页工具类
     */
    void page(PageUtil<BookVo> page, BookVo bookVo);

    /**
     * 前台使用
     * @param page
     * @param bookVo
     */
    void queryBookList(PageUtil<BookVo> page, BookVo bookVo);

    /**
     * 查询图书标签
     * @param id
     * @return
     */
    BookVo queryBookTags(Integer id);

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    boolean save(Book book);

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    boolean update(Book book);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(List<Integer> id);

}
