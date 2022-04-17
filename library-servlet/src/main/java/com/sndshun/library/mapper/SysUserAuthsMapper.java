package com.sndshun.library.mapper;


import com.sndshun.library.entity.SysUserAuths;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 用户授权表(SysUserAuths)表数据库访问层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:08
 */
@Mapper
public interface SysUserAuthsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserAuths queryById(Integer id);

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
    List<SysUserAuths> queryAllByLimit(@Param("ew") PageUtil<SysUserAuths> page);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserAuths 实例对象
     * @return 对象列表
     */
    List<SysUserAuths> queryAll(SysUserAuths sysUserAuths);

    /**
     * 新增数据
     *
     * @param sysUserAuths 实例对象
     * @return 影响行数
     */
    int insert(SysUserAuths sysUserAuths);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserAuths> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserAuths> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserAuths> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUserAuths> entities);

    /**
     * 修改数据
     *
     * @param sysUserAuths 实例对象
     * @return 影响行数
     */
    int update(SysUserAuths sysUserAuths);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

