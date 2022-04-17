package com.sndshun.library.controller;


import com.sndshun.library.entity.SysRolePermission;
import com.sndshun.library.service.SysRolePermissionService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import com.sndshun.library.vo.RoleMenuPerVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限映射表
 * <p>
 * (SysRolePermission)表控制层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:06
 */
@Slf4j
@RestController
@RequestMapping("sysRolePermission")
public class SysRolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @RequestMapping("getRoleIsMenuPer")
    public Result<List<RoleMenuPerVo>> getRoleIsMenuPer() {
        return Result.success(sysRolePermissionService.queryRoleIsMenuPer());
    }

    @GetMapping("page")
    public Result<PageUtil<SysRolePermission>> page(PageUtil<SysRolePermission> pageUtil) {
        this.sysRolePermissionService.page(pageUtil);

        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<SysRolePermission> selectOne(@PathVariable Integer id) {
        return Result.success(this.sysRolePermissionService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody SysRolePermission sysRolePermission) {
        return Result.success(this.sysRolePermissionService.save(sysRolePermission));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody SysRolePermission sysRolePermission) {
        return Result.success(this.sysRolePermissionService.update(sysRolePermission));
    }


    @DeleteMapping
    public Result<Boolean> delete(Integer id) {
        return Result.success(this.sysRolePermissionService.removeById(id));
    }

    @PostMapping("updRolePer")
    public Result updRolePer(@RequestBody List<Integer> permissions, Integer rid, String name,Integer uid) {
        log.debug("uid===========================》"+uid);
        log.debug("权限==========================》"+permissions.size());
        log.debug("rid==========================》"+rid);
        log.debug("name==========================》"+name);
        return Result.success(sysRolePermissionService.updRolePer(permissions,rid,name,uid));
    }
}
