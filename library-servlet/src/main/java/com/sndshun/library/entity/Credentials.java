package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 借阅凭证表(Credentials)表实体类
 *
 * @author sndshun
 * @since 2022-05-12 19:15:45
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credentials implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */

    private Integer id;
    /**
     * 凭证名
     */

    private String credentialsName;
    /**
     * 需交押金
     */

    private Double credentialsPrice;
    /**
     * 可借阅数量
     */

    private Integer borrowingCount;
    /**
     * 可借阅天数
     */

    private Integer borrowingTime;
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
