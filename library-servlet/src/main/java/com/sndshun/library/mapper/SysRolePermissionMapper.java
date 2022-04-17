package com.sndshun.library.mapper;


import com.sndshun.library.entity.SysRolePermission;
import com.sndshun.library.vo.RoleMenuPerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sndshun.library.utils.PageUtil;

import java.util.List;

/**
 * 角色权限映射表
 * <p>
 * (SysRolePermission)表数据库访问层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:06
 */
@Mapper
public interface SysRolePermissionMapper {

    /**
     * 查询所有角色下权限
     * @return
     */
    List<RoleMenuPerVo> queryRoleIsMenuPer();


    /**
     * 修改角色权限
     * @param permissions
     * @param rid
     * @param uid
     * @return
     */
    int updRolePer(@Param("permissions") List<Integer> permissions,@Param("rid") Integer rid,@Param("uid") Integer uid);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRolePermission queryById(Integer id);



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
    List<SysRolePermission> queryAllByLimit(@Param("ew") PageUtil<SysRolePermission> page);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRolePermission 实例对象
     * @return 对象列表
     */
    List<SysRolePermission> queryAll(SysRolePermission sysRolePermission);

    /**
     * 新增数据
     *
     * @param sysRolePermission 实例对象
     * @return 影响行数
     */
    int insert(SysRolePermission sysRolePermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRolePermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRolePermission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRolePermission> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRolePermission> entities);

    /**
     * 修改数据
     *
     * @param sysRolePermission 实例对象
     * @return 影响行数
     */
    int update(SysRolePermission sysRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

