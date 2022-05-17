package com.sndshun.library.mapper;


import com.sndshun.library.entity.Credentials;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 借阅凭证表(Credentials)表数据库访问层
 *
 * @author sndshun
 * @since 2022-05-12 19:15:45
 */
@Mapper
public interface CredentialsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Credentials queryById(Integer id);

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
    List<Credentials> queryAllByLimit(@Param("ew") PageUtil<Credentials> page);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param credentials 实例对象
     * @return 对象列表
     */
    List<Credentials> queryAll(Credentials credentials);

    /**
     * 新增数据
     *
     * @param credentials 实例对象
     * @return 影响行数
     */
    int insert(Credentials credentials);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Credentials> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Credentials> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Credentials> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Credentials> entities);

    /**
     * 修改数据
     *
     * @param credentials 实例对象
     * @return 影响行数
     */
    int update(Credentials credentials);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过主键删除数据（MyBatis原生foreach方法）
     * @param ids
     * @return
     */
    int deleteBatch(@Param("entities") List<Integer> ids);

}

