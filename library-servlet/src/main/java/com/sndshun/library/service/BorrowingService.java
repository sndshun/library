package com.sndshun.library.service;


import com.sndshun.library.entity.Borrowing;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 借阅记录表(Borrowing)表服务接口
 *
 * @author sndshun
 * @since 2022-03-26 17:16:03
 */
public interface BorrowingService {

    /**
     * 通过ID查询借阅书籍
     *
     * @param id 主键
     * @return 实例对象
     */
    List<Borrowing> borrowingBooks(Integer id);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Borrowing getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param borrowing 实例对象
     * @return 对象列表
     */
    List<Borrowing> list(Borrowing borrowing);

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    void page(PageUtil<Borrowing> page,String title,String name);

    /**
     * 新增数据
     *
     * @param borrowing 实例对象
     * @return 实例对象
     */
    boolean save(Borrowing borrowing,Integer currentNumber);

    /**
     * 修改数据
     *
     * @param borrowing 实例对象
     * @return 实例对象
     */
    boolean update(Borrowing borrowing);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id);

}
