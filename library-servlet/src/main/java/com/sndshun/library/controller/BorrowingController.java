package com.sndshun.library.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sndshun.library.entity.Borrowing;
import com.sndshun.library.service.BorrowingService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @RequestMapping("borrowingBooks")
    public Result<List<Borrowing>> borrowingBooks(Integer id) {
        return Result.success(borrowingService.borrowingBooks(id));
    }
    @GetMapping("page")
    public Result<PageUtil<Borrowing>> page(Long id, String title,Integer state,PageUtil<Borrowing> pageUtil) {
        this.borrowingService.page(pageUtil,id,title,state);
        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<Borrowing> selectOne(@PathVariable Long id) {
        return Result.success(this.borrowingService.getById(id));
    }

    @SaCheckPermission("borrowing:add")
    @PostMapping
    public Result<Boolean> insert(@RequestBody Borrowing borrowing) {
        borrowing.setStartTime(new Date());
        //订单号 自动生成 年月日时分秒+uid
        String orderNumber = String.valueOf(System.currentTimeMillis())+borrowing.getUserId();
        borrowing.setId(Long.valueOf(orderNumber));
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
