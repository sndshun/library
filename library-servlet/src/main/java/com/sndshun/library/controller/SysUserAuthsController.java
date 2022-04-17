package com.sndshun.library.controller;


import com.sndshun.library.entity.SysUserAuths;
import com.sndshun.library.service.SysUserAuthsService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户授权表(SysUserAuths)表控制层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:08
 */
@RestController
@RequestMapping("sysUserAuths")
public class SysUserAuthsController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserAuthsService sysUserAuthsService;

    @GetMapping("page")
    public Result<PageUtil<SysUserAuths>> page(PageUtil<SysUserAuths> pageUtil) {
        this.sysUserAuthsService.page(pageUtil);

        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<SysUserAuths> selectOne(@PathVariable Integer id) {
        return Result.success(this.sysUserAuthsService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody SysUserAuths sysUserAuths) {
        return Result.success(this.sysUserAuthsService.save(sysUserAuths));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody SysUserAuths sysUserAuths) {
        return Result.success(this.sysUserAuthsService.update(sysUserAuths));
    }


    @DeleteMapping
    public Result<Boolean> delete(Integer id) {
        return Result.success(this.sysUserAuthsService.removeById(id));
    }

}
