package com.sndshun.library.service.impl;


import com.sndshun.library.mapper.BorrowingMapper;
import com.sndshun.library.entity.Borrowing;
import com.sndshun.library.service.BorrowingService;
import com.sndshun.library.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Borrowing getById(Integer id) {
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
    public void page(PageUtil<Borrowing> page) {
        page.setTotal(this.borrowingMapper.queryCount());
        List<Borrowing> list = this.borrowingMapper.queryAllByLimit(page);
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
