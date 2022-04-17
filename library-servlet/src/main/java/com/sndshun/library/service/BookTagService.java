package com.sndshun.library.service;


import com.sndshun.library.entity.BookTag;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.vo.BookTagVo;

import java.util.List;

/**
 * 书籍标签表(BookTag)表服务接口
 *
 * @author sndshun
 * @since 2022-03-26 17:16:01
 */
public interface BookTagService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BookTag getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param bookTag 实例对象
     * @return 对象列表
     */
    List<BookTag> list(BookTag bookTag);

    /**
     * 分页查询
     * @param bookTag 条件
     * @param page 分页工具类
     */
    void page(PageUtil<BookTagVo> page, BookTagVo bookTag);

    /**
     * 新增数据
     *
     * @param bookTag 实例对象
     * @return 实例对象
     */
    boolean save(BookTag bookTag);

    /**
     * 修改数据
     *
     * @param bookTag 实例对象
     * @return 实例对象
     */
    boolean update(BookTag bookTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(List<Integer> ids);

}
