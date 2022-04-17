package com.sndshun.library.mapper;


import com.sndshun.library.entity.BookTags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * (BookTags)表数据库访问层
 *
 * @author sndshun
 * @since 2022-04-08 16:42:44
 */
@Mapper
public interface BookTagsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BookTags queryById(Integer id);

    /**
     * 查询总数
     * @return 总条数
     */
    int queryCount();

    /**
     * 查询指定行数据
     *
     * @param page 分页工具类
     * @return 对象列表
     */
    List<BookTags> queryAllByLimit(@Param("ew")PageUtil<BookTags> page);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param bookTags 实例对象
     * @return 对象列表
     */
    List<BookTags> queryAll(BookTags bookTags);

    /**
     * 新增数据
     *
     * @param bookTags 实例对象
     * @return 影响行数
     */
    int insert(BookTags bookTags);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BookTags> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BookTags> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BookTags> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<BookTags> entities);

    /**
     * 修改数据
     *
     * @param bookTags 实例对象
     * @return 影响行数
     */
    int update(BookTags bookTags);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过图书id删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteByBookId(Integer id);


    /**
     * 通过图书id批量删除数据
     *
     * @param ids
     * @return
     */
    int deleteBatchByBookId(@Param("entities") List<Integer> ids);

}

