package com.sndshun.library.mapper;


import com.sndshun.library.dto.UserInfoDTO;
import com.sndshun.library.entity.SysPermission;
import com.sndshun.library.vo.MenuPerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sndshun.library.utils.PageUtil;

import java.util.List;
import java.util.Set;

/**
 * 权限表(SysPermission)表数据库访问层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:04
 */
@Mapper
public interface SysPermissionMapper {


    /**
     * 获取所有菜单
     * @return
     */
    Set<String> getAllMenus();

    /**
     * 获取所有权限
     * @return
     */
    Set<String> getAllPermission();

    /**
     * 查询所有权限
     * @return
     */
    List<MenuPerVo> getAllIDAndPermission();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysPermission queryById(Integer id);

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
    List<SysPermission> queryAllByLimit(@Param("ew") PageUtil<SysPermission> page);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPermission 实例对象
     * @return 对象列表
     */
    List<SysPermission> queryAll(SysPermission sysPermission);

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 影响行数
     */
    int insert(SysPermission sysPermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysPermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysPermission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysPermission> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SysPermission> entities);

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 影响行数
     */
    int update(SysPermission sysPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

