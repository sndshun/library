package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 书籍类型表(BookType)表实体类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookType implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 书籍类型id
     */

    private Integer id;
    /**
     * 书籍类型
     */

    private String typeName;
    /**
     * 更新者
     */

    private Integer updateBy;
    /**
     * 创建者
     */

    private Integer createBy;
    /**
     * 更新时间
     */

    private Date updateTime;
    /**
     * 创建时间
     */

    private Date createTime;

}
