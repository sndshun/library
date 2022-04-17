package com.sndshun.library.service;


import com.sndshun.library.entity.BookType;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.vo.BookTypeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 书籍类型表(BookType)表服务接口
 *
 * @author sndshun
 * @since 2022-03-26 17:16:02
 */
public interface BookTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BookType getById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param bookType 实例对象
     * @return 对象列表
     */
    List<BookType> list(BookType bookType);

    /**
     * 分页查询
     *
     * @param page 分页工具类
     * @param vo 条件
     */
    void page(PageUtil<BookTypeVo> page, BookTypeVo vo);

    /**
     * 新增数据
     *
     * @param bookType 实例对象
     * @return 实例对象
     */
    boolean save(BookType bookType);

    /**
     * 修改数据
     *
     * @param bookType 实例对象
     * @return 实例对象
     */
    boolean update(BookType bookType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id);
    /**
     * 批量删除
     * @param list
     * @return
     */
    boolean deleteBatch(@Param("entities") List<Integer> list);
}
