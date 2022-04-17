package com.sndshun.library.vo;

import com.sndshun.library.entity.Book;
import com.sndshun.library.entity.BookTag;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Author mr.sun
 * @Creation 2022 2022/4/9 19:37
 * @Desc:
 */
@Data
public class BookVo extends Book {
    /**
     * 创建者
     */

    private String createName;
    /**
     * 更新者
     */

    private String updateName;

    /**
     * 分类名称
     */
    private String typeName;


    /**
     * 图书标签
     */
    private List<BookTag> tags;


    /**
     * 这个字段是用来接收前台传过来的图书标签
     */
    private Set<Integer> tagId;

    /**
     * 封面
     */

    private String imgUrl;
    /**
     * 图片描述
     */
    private String imgDesc;

    /**
     * 跳转
     */
    private String dataHref;
}
