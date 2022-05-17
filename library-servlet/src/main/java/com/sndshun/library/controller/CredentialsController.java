package com.sndshun.library.controller;


import com.sndshun.library.entity.Credentials;
import com.sndshun.library.service.CredentialsService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 借阅凭证表(Credentials)表控制层
 *
 * @author sndshun
 * @since 2022-05-12 19:15:45
 */
@RestController
@RequestMapping("credentials")
public class CredentialsController {
    /**
     * 服务对象
     */
    @Resource
    private CredentialsService credentialsService;

    @GetMapping("list")
    public Result<List<Credentials>> list(Credentials credentials) {
        return Result.success(this.credentialsService.list(credentials));
    }
    @GetMapping("page")
    public Result<PageUtil<Credentials>> page(PageUtil<Credentials> pageUtil) {
        this.credentialsService.page(pageUtil);
        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<Credentials> selectOne(@PathVariable Integer id) {
        return Result.success(this.credentialsService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody Credentials credentials) {
        return Result.success(this.credentialsService.save(credentials));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody Credentials credentials) {
        return Result.success(this.credentialsService.update(credentials));
    }


    @DeleteMapping
    public Result<Boolean> delete(@RequestBody List<Integer> id) {
        return Result.success(this.credentialsService.removeById(id));
    }

}
