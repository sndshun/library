package com.sndshun.library.controller;


import com.sndshun.library.entity.BookTag;
import com.sndshun.library.entity.BookType;
import com.sndshun.library.service.BookTagService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import com.sndshun.library.vo.BookTagVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书籍标签表(BookTag)表控制层
 *
 * @author sndshun
 * @since 2022-03-26 17:16:01
 */
@RestController
@RequestMapping("bookTag")
public class BookTagController {
    /**
     * 服务对象
     */
    @Resource
    private BookTagService bookTagService;

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @RequestMapping("list")
    public Result<List<BookTag>> list(BookTag bookTag) {
        return Result.success(this.bookTagService.list(bookTag));
    }

    @RequestMapping("page")
    public Result<PageUtil<BookTagVo>> page(BookTagVo bookTagVo, PageUtil<BookTagVo> pageUtil) {
        this.bookTagService.page(pageUtil, bookTagVo);
        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<BookTag> selectOne(@PathVariable Integer id) {
        return Result.success(this.bookTagService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody BookTag bookTag) {
        return Result.success(this.bookTagService.save(bookTag));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody BookTag bookTag) {
        return Result.success(this.bookTagService.update(bookTag));
    }


    @DeleteMapping
    public Result<Boolean> delete(Integer id) {
        return Result.success(this.bookTagService.removeById(id));
    }

    @DeleteMapping("batchDel")
    public Result<Boolean> batchDel(@RequestBody List<Integer> ids) {
        return Result.success(this.bookTagService.deleteBatch(ids));
    }

}
