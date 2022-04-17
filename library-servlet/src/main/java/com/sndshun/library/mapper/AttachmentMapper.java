package com.sndshun.library.mapper;


import com.sndshun.library.entity.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * (Attachment)表数据库访问层
 *
 * @author sndshun
 * @since 2022-04-15 16:35:25
 */
@Mapper
public interface AttachmentMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Attachment queryById(String id);

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
    List<Attachment> queryAllByLimit(@Param("ew") PageUtil<Attachment> page);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param attachment 实例对象
     * @return 对象列表
     */
    List<Attachment> queryAll(Attachment attachment);

    /**
     * 新增数据
     *
     * @param attachment 实例对象
     * @return 影响行数
     */
    int insert(Attachment attachment);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Attachment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Attachment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Attachment> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Attachment> entities);

    /**
     * 修改数据
     *
     * @param attachment 实例对象
     * @return 影响行数
     */
    int update(Attachment attachment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

