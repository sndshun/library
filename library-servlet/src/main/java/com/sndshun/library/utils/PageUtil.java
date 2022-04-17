package com.sndshun.library.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义分页工具
 *
 * @author sndshun
 * @since 2022-03-26 17:16:09
 */
public class PageUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当前页
     */
    private Integer current = 1;
    /**
     * 页大小
     */
    private Integer size = 10;
    /**
     * 总条数
     */
    private Integer total = 0;
    /**
     * 总页数
     */
    private Integer pages = 0;
    /**
     * 数据集合
     */
    private List<T> list;


    /**
     * 起始游标
     * 序列化时忽略
     */

    private int start;

    @JsonIgnore
    public int getStart() {
        return this.size * (this.current - 1);
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        if (null == current) {
            current = 1;
        } else if (current < 1) {
            current = 1;
        }
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    /**
     * 从此初始化
     *
     * @param total 总条目数
     */
    public void setTotal(Integer total) {
        if (0 == total) {
            this.pages = 0;
            this.current = 1;
        } else {
            this.pages = (int) Math.ceil(total * 1.0 / this.size);
            if (this.pages < this.current) {
                this.current = this.pages;
            }
        }
        this.total = total;
    }


    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
