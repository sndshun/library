package com.sndshun.library.controller;


import com.sndshun.library.entity.BookType;
import com.sndshun.library.service.BookTypeService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import com.sndshun.library.vo.BookTypeVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书籍类型表(BookType)表控制层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:02
 */
@RestController
@RequestMapping("bookType")
public class BookTypeController {
    /**
     * 服务对象
     */
    @Resource
    private BookTypeService bookTypeService;

    @RequestMapping("list")
    public Result<List<BookType>> list(BookType bookType) {
        return Result.success(this.bookTypeService.list(bookType));
    }


    @RequestMapping("page")
    public Result<PageUtil<BookTypeVo>> page(BookTypeVo searchForm, PageUtil<BookTypeVo> pageUtil) {
        this.bookTypeService.page(pageUtil,searchForm);
        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<BookType> selectOne(@PathVariable Integer id) {
        return Result.success(this.bookTypeService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody BookType bookType) {
        return Result.success(this.bookTypeService.save(bookType));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody BookType bookType) {
        return Result.success(this.bookTypeService.update(bookType));
    }


    @DeleteMapping
    public Result<Boolean> delete(Integer id) {
        return Result.success(this.bookTypeService.removeById(id));
    }

    @DeleteMapping("batchDel")
    public Result<Boolean> batchDel(@RequestBody List<Integer> ids) {
        return Result.success(bookTypeService.deleteBatch(ids));
    }

}
