package com.sndshun.library.mapper;


import com.sndshun.library.entity.BookTag;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.vo.BookTagVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 书籍标签表(BookTag)表数据库访问层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:01
 */
@Mapper
public interface BookTagMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BookTag queryById(Integer id);

    /**
     * 查询总数
     *
     * @param vo 条件
     * @return 总条数
     */
    int queryCount(@Param("vo") BookTagVo vo);

    /**
     * 查询指定行数据
     *
     * @param page    分页工具类
     * @param bookTag 条件
     * @return 对象列表
     */
    List<BookTagVo> queryAllByLimit(@Param("ew") PageUtil<BookTagVo> page, @Param("vo") BookTagVo bookTag);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param bookTag 实例对象
     * @return 对象列表
     */
    List<BookTag> queryAll(BookTag bookTag);

    /**
     * 新增数据
     *
     * @param bookTag 实例对象
     * @return 影响行数
     */
    int insert(BookTag bookTag);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BookTag> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BookTag> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BookTag> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<BookTag> entities);

    /**
     * 修改数据
     *
     * @param bookTag 实例对象
     * @return 影响行数
     */
    int update(BookTag bookTag);

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

