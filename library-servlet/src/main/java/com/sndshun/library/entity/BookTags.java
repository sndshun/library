package com.sndshun.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (BookTags)表实体类
 *
 * @author sndshun
 * @since 2022-04-08 16:42:44
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTags implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */

    private Integer id;
    /**
     * 书籍id
     */

    private Integer bookId;
    /**
     * 标签id
     */

    private Integer tagId;
    /**
     * 推荐数
     */

    private Integer likes;
    /**
     * 最后修改人
     */

    private Integer updateBy;
    /**
     * 最后修改时间
     */

    private Date updateTime;
    /**
     * 创建人
     */

    private Integer createBy;
    /**
     * 创建时间
     */

    private Date createTime;

}
