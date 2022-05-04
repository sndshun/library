package com.sndshun.library.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sndshun.library.entity.Book;
import com.sndshun.library.service.BookService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import com.sndshun.library.vo.BookVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 书籍信息表(Book)表控制层
 *
 * @author sndshun
 * @since 2022-03-26 17:15:58
 */
@RestController
@RequestMapping("book")
public class BookController {
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;



    @GetMapping("page")
    public Result<PageUtil<BookVo>> page(PageUtil<BookVo> pageUtil, BookVo bookVo) {
        this.bookService.page(pageUtil,bookVo);
        return Result.success(pageUtil);
    }

    @GetMapping("list")
    public Result<PageUtil<BookVo>> list(PageUtil<BookVo> pageUtil, BookVo bookVo) {
        this.bookService.queryBookList(pageUtil,bookVo);
        return Result.success(pageUtil);
    }

    @GetMapping("getTags/{id}")
    public Result<BookVo> getTags(@PathVariable("id") Integer id) {
        return Result.success(this.bookService.queryBookTags(id));
    }


    @GetMapping("{id}")
    public Result<BookVo> selectOne(@PathVariable Integer id) {
        return Result.success(this.bookService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody Book book) {
        book.setCurrentNumber(book.getTotal());
        return Result.success(this.bookService.save(book));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody Book book) {

        return Result.success(this.bookService.update(book));
    }


    @DeleteMapping
    public Result<Boolean> delete(@RequestBody List<Integer> ids) {
        return Result.success(this.bookService.removeById(ids));
    }

}
