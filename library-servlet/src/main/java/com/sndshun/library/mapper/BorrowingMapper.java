package com.sndshun.library.mapper;


import com.sndshun.library.entity.Borrowing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 借阅记录表(Borrowing)表数据库访问层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:03
 */
@Mapper
public interface BorrowingMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Borrowing queryById(Integer id);

    /**
     * 查询总数
     *
     * @return 总条数
     */
    int queryCount();

    /**
     * 查询指定行数据
     *
     * @param page 分页工具类
     * @return 对象列表
     */
    List<Borrowing> queryAllByLimit(@Param("ew") PageUtil<Borrowing> page);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param borrowing 实例对象
     * @return 对象列表
     */
    List<Borrowing> queryAll(Borrowing borrowing);

    /**
     * 新增数据
     *
     * @param borrowing 实例对象
     * @return 影响行数
     */
    int insert(Borrowing borrowing);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Borrowing> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Borrowing> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Borrowing> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Borrowing> entities);

    /**
     * 修改数据
     *
     * @param borrowing 实例对象
     * @return 影响行数
     */
    int update(Borrowing borrowing);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

