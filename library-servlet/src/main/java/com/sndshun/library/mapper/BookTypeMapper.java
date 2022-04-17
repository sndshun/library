package com.sndshun.library.mapper;


import com.sndshun.library.entity.BookType;
import com.sndshun.library.vo.BookTypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 书籍类型表(BookType)表数据库访问层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:02
 */
@Mapper
public interface BookTypeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BookType queryById(Integer id);

    /**
     * 查询总数
     *
     * @return 总条数
     * @param vo 条件
     */
    int queryCount(@Param("vo") BookTypeVo vo);

    /**
     * 查询指定行数据
     *
     * @param page 分页工具类
     * @param vo 条件
     * @return 对象列表
     */
    List<BookTypeVo> queryAllByLimit(@Param("ew") PageUtil<BookTypeVo> page, @Param("vo") BookTypeVo vo);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param bookType 实例对象
     * @return 对象列表
     */
    List<BookType> queryAll(BookType bookType);

    /**
     * 新增数据
     *
     * @param bookType 实例对象
     * @return 影响行数
     */
    int insert(BookType bookType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BookType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BookType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BookType> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<BookType> entities);

    /**
     * 修改数据
     *
     * @param bookType 实例对象
     * @return 影响行数
     */
    int update(BookType bookType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 批量删除
     * @param list
     * @return
     */
    int deleteBatch(@Param("entities") List<Integer> list);
}

