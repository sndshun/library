package com.sndshun.library.controller;


import com.sndshun.library.entity.Borrowing;
import com.sndshun.library.service.BorrowingService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 借阅记录表(Borrowing)表控制层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:03
 */
@RestController
@RequestMapping("borrowing")
public class BorrowingController {
    /**
     * 服务对象
     */
    @Resource
    private BorrowingService borrowingService;

    @GetMapping("page")
    public Result<PageUtil<Borrowing>> page(PageUtil<Borrowing> pageUtil) {
        this.borrowingService.page(pageUtil);

        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<Borrowing> selectOne(@PathVariable Integer id) {
        return Result.success(this.borrowingService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody Borrowing borrowing) {
        return Result.success(this.borrowingService.save(borrowing));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody Borrowing borrowing) {
        return Result.success(this.borrowingService.update(borrowing));
    }


    @DeleteMapping
    public Result<Boolean> delete(Integer id) {
        return Result.success(this.borrowingService.removeById(id));
    }

}
