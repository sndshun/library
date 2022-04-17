package com.sndshun.library.controller;




import com.sndshun.library.entity.BookTags;
import com.sndshun.library.service.BookTagsService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (BookTags)表控制层
 *
 * @author sndshun
 * @since 2022-04-08 16:42:44
 */
@RestController
@RequestMapping("bookTags")
public class BookTagsController {
    /**
     * 服务对象
     */
    @Resource
    private BookTagsService bookTagsService;

    @GetMapping("page")
    public Result<PageUtil<BookTags>> page(PageUtil<BookTags> pageUtil){
      this.bookTagsService.page(pageUtil);

       return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<BookTags> selectOne(@PathVariable Integer id) {
        return Result.success(this.bookTagsService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody BookTags bookTags) {
        return Result.success(this.bookTagsService.save(bookTags));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody BookTags bookTags) {
        return Result.success(this.bookTagsService.update(bookTags));
    }


     @DeleteMapping
    public Result<Boolean> delete(Integer id) {
        return Result.success(this.bookTagsService.removeById(id));
    }

}
