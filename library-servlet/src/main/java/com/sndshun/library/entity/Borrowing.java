package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 借阅记录表(Borrowing)表实体类
 *
 * @author sndshun
 * @since 2022-03-29 18:41:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrowing implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 借阅记录id
     */

    private Integer id;
    /**
     * 借阅书籍id
     */

    private Integer bookId;
    /**
     * 借阅用户id
     */

    private Integer userId;
    /**
     * 借阅时间
     */

    private Date startTime;
    /**
     * 归还时间
     */

    private Date endTime;
    /**
     * 修改者
     */

    private Integer updateBy;
    /**
     * 创建者
     */

    private Integer createBy;
    /**
     * 修改时间
     */

    private Date updateTime;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 状态0（未归还）1（已归还）
     */

    private Integer state;

}
