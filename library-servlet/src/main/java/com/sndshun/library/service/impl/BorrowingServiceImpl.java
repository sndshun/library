package com.sndshun.library.service.impl;


import com.sndshun.library.entity.Book;
import com.sndshun.library.mapper.BookMapper;
import com.sndshun.library.mapper.BorrowingMapper;
import com.sndshun.library.entity.Borrowing;
import com.sndshun.library.service.BookService;
import com.sndshun.library.service.BorrowingService;
import com.sndshun.library.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 借阅记录表(Borrowing)表服务实现类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:03
 */
@Service("borrowingService")
public class BorrowingServiceImpl implements BorrowingService {
    @Resource
    private BorrowingMapper borrowingMapper;
    @Resource
    private BookMapper bookMapper;


    @Override
    public List<Borrowing> borrowingBooks(Integer id) {
        return this.borrowingMapper.borrowingBooks(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Borrowing getById(Long id) {
        return this.borrowingMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param borrowing 主键
     * @return 实例对象
     */
    @Override
    public List<Borrowing> list(Borrowing borrowing) {
        return this.borrowingMapper.queryAll(borrowing);
    }

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<Borrowing> page,Long id,String title,Integer state) {
        page.setTotal(this.borrowingMapper.queryCount(id,title,state));
        List<Borrowing> list = this.borrowingMapper.queryAllByLimit(page,id,title,state);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param borrowing 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(Borrowing borrowing) {
        Book book = bookMapper.queryById(borrowing.getBookId());

        Book book1 = new Book();
        book1.setId(borrowing.getBookId());
        book1.setCurrentNumber(book.getCurrentNumber()-1);
        book1.setBorrows(book.getBorrows()+1);

        this.bookMapper.update(book1);
        return this.borrowingMapper.insert(borrowing) > 0;
    }

    /**
     * 修改数据
     *
     * @param borrowing 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Borrowing borrowing) {
        Book book = borrowing.getBook();
        Borrowing borrowing1 = this.borrowingMapper.queryById(borrowing.getId());
        if(borrowing1.getState()==0||borrowing1.getState()==4){
            if(borrowing.getState()==1){
                book.setCurrentNumber(book.getCurrentNumber()+1);
            }else if(borrowing.getState()==2){
                book.setCurrentNumber(book.getCurrentNumber()+1);
            }else if(borrowing.getState()==3){
                book.setCurrentNumber(book.getCurrentNumber()+1);
            }
        }else {
            if(borrowing.getState()==0||borrowing.getState()==4){
                book.setCurrentNumber(book.getCurrentNumber()-1);
            }
        }



        borrowing.setEndTime(new Date());
        book.setId(borrowing.getBookId());
        this.bookMapper.update(book);
        return this.borrowingMapper.update(borrowing) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.borrowingMapper.deleteById(id) > 0;
    }
}
