package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 书籍信息表(Book)表实体类
 *
 * @author sndshun
 * @since 2022-04-04 15:22:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 书籍id
     */

    private Integer id;
    /**
     * 书籍名称
     */

    private String title;
    /**
     * 副标题
     */

    private String subTitle;
    /**
     * 源标题(国外源标题)
     */

    private String originTitle;
    /**
     * 作者
     */

    private String author;
    /**
     * 书籍分类
     */

    private Integer bookType;

    /**
     * 装订方式
     */

    private String bindng;
    /**
     * 总页数
     */

    private Integer pages;
    /**
     * 附件id
     */

    private String attachmentId;


    /**
     * 10位ISNB码
     */

    private String isbn10;
    /**
     * 13为ISNB码
     */

    private String isbn13;
    /**
     * 书籍简介
     */

    private String summary;
    /**
     * 原文摘录
     */

    private String originalTexts;
    /**
     * 销售价格
     */

    private Double price;
    /**
     * 书籍总量
     */

    private Integer total;
    /**
     * 现存数量（可借阅数量）
     */

    private Integer currentNumber;
    /**
     * 更新者
     */

    private String updateBy;
    /**
     * 创建者
     */

    private String createBy;
    /**
     * 更新时间
     */

    private Date updateTime;
    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 丛书
     */

    private String brand;
    /**
     * 出版社名称
     */

    private String publisher;
    /**
     * 发版日期
     */

    private Date pubdate;
    /**
     * 推荐人数（点赞数）
     */

    private Integer likes;
    /**
     * 借阅人数
     */

    private Integer borrows;

}
