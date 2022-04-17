package com.sndshun.library.controller;


import com.sndshun.library.entity.SysRole;
import com.sndshun.library.service.SysRoleService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户角色表(SysRole)表控制层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:05
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    @GetMapping("page")
    public Result<PageUtil<SysRole>> page(PageUtil<SysRole> pageUtil) {
        this.sysRoleService.page(pageUtil);

        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<SysRole> selectOne(@PathVariable Integer id) {
        return Result.success(this.sysRoleService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody SysRole sysRole) {
        return Result.success(this.sysRoleService.save(sysRole));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody SysRole sysRole) {
        return Result.success(this.sysRoleService.update(sysRole));
    }


    @DeleteMapping
    public Result<Boolean> delete(Integer id) {
        return Result.success(this.sysRoleService.removeById(id));
    }

}
