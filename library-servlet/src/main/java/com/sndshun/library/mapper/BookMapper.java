package com.sndshun.library.mapper;


import com.sndshun.library.entity.Book;
import com.sndshun.library.vo.BookVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 书籍信息表(Book)表数据库访问层
 *
 * @author sndshun
 * @since 2022-03-26 17:15:58
 */
@Mapper
public interface BookMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BookVo queryById(Integer id);

    /**
     * 查询总数
     * @param book 查询条件
     * @return 总条数
     */
    int queryCount(@Param("vo") BookVo book);

    /**
     * 查询指定行数据
     *
     * @param page 分页工具类
     * @param book 查询条件
     * @return 对象列表
     */
    List<BookVo> queryAllByLimit(@Param("ew") PageUtil<BookVo> page, @Param("vo") BookVo book);

    /**
     * 前台查询所有图书
     * @param page
     * @param book
     * @return
     */
    List<BookVo> queryBookList(@Param("ew") PageUtil<BookVo> page, @Param("vo") BookVo book);

    /**
     * 查询图书标签
     * @param id
     * @return
     */
    BookVo queryBookTags(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param book 实例对象
     * @return 对象列表
     */
    List<Book> queryAll(Book book);

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 影响行数
     */
    int insert(Book book);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Book> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Book> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Book> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Book> entities);

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 影响行数
     */
    int update(Book book);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteBatch(@Param("entities") List<Integer> ids);

}

