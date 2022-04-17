package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 书籍标签表(BookTag)表实体类
 *
 * @author sndshun
 * @since 2022-04-07 15:21:39
 */

@Data
public class BookTag implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 标签id
     */

    private Integer id;
    /**
     * 标签名字
     */

    private String tagName;
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
    /**
     * 标签状态：
     * 1（待审核状态）
     * 2（正常状态）
     */

    private Integer tagState;

    /**
     * 这是一个附加属性，用于存储查询结果
     * 在每本图书上的点赞数
     */
    private Integer tagLikes;

    /**
     * 这是一个附加属性，用于存储查询结果
     */
    private Integer bookTagsId;

}
