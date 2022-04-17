package com.sndshun.library.controller;


import com.sndshun.library.entity.SysPermission;
import com.sndshun.library.service.SysPermissionService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import com.sndshun.library.vo.MenuPerVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(SysPermission)表控制层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:04
 */
@RestController
@RequestMapping("sysPermission")
public class SysPermissionController {
    /**
     * 服务对象
     */
    @Resource
    private SysPermissionService sysPermissionService;

    @RequestMapping("getAllPer")
    public Result<List<MenuPerVo>> getAllPer() {
        return Result.success(sysPermissionService.getAllIDAndPermission());
    }

    @GetMapping("page")
    public Result<PageUtil<SysPermission>> page(PageUtil<SysPermission> pageUtil) {
        this.sysPermissionService.page(pageUtil);

        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<SysPermission> selectOne(@PathVariable Integer id) {
        return Result.success(this.sysPermissionService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody SysPermission sysPermission) {
        return Result.success(this.sysPermissionService.save(sysPermission));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody SysPermission sysPermission) {
        return Result.success(this.sysPermissionService.update(sysPermission));
    }


    @DeleteMapping
    public Result<Boolean> delete(Integer id) {
        return Result.success(this.sysPermissionService.removeById(id));
    }

}
