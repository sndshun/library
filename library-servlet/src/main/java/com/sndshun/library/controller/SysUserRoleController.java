package com.sndshun.library.controller;


import com.sndshun.library.entity.SysUserRole;
import com.sndshun.library.service.SysUserRoleService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户角色关联表(SysUserRole)表控制层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:09
 */
@RestController
@RequestMapping("sysUserRole")
public class SysUserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserRoleService sysUserRoleService;

    @GetMapping("page")
    public Result<PageUtil<SysUserRole>> page(PageUtil<SysUserRole> pageUtil) {
        this.sysUserRoleService.page(pageUtil);

        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<SysUserRole> selectOne(@PathVariable Integer id) {
        return Result.success(this.sysUserRoleService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody SysUserRole sysUserRole) {
        return Result.success(this.sysUserRoleService.save(sysUserRole));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody SysUserRole sysUserRole) {
        return Result.success(this.sysUserRoleService.update(sysUserRole));
    }


    @DeleteMapping
    public Result<Boolean> delete(Integer id) {
        return Result.success(this.sysUserRoleService.removeById(id));
    }

}
