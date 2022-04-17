package com.sndshun.library.controller;


import com.sndshun.library.entity.SysUser;
import com.sndshun.library.service.SysUserService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:07
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    @GetMapping("page")
    public Result<PageUtil<SysUser>> page(PageUtil<SysUser> pageUtil) {
        this.sysUserService.page(pageUtil);

        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<SysUser> selectOne(@PathVariable Integer id) {
        return Result.success(this.sysUserService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody SysUser sysUser) {
        return Result.success(this.sysUserService.save(sysUser));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody SysUser sysUser) {
        return Result.success(this.sysUserService.update(sysUser));
    }


    @DeleteMapping
    public Result<Boolean> delete(Integer id) {
        return Result.success(this.sysUserService.removeById(id));
    }

}
