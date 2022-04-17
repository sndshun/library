package com.sndshun.library.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Attachment)表实体类
 *
 * @author sndshun
 * @since 2022-04-16 13:09:31
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */

    private String id;
    /**
     * 图片名称
     */

    private String imgName;
    /**
     * 后缀
     */

    private String suffix;
    /**
     * 默认单位byte
     */

    private String size;
    /**
     * 标题
     */

    private String imgTitle;
    /**
     * 描述
     */

    private String imgDesc;
    /**
     * 访问路径
     */

    private String imgUrl;
    /**
     * 真实路径
     */

    private String absolutePath;
    /**
     * 1:正常0:删除
     */

    private Integer isDelete;
    /**
     * 默认在回收站30天删除
     * 通过定时任务每天根据这个字段查询到删除时间的图片
     */

    private Date deleteTime;
    /**
     * 查看是谁放回收站的
     */

    private Integer deleteBy;
    /**
     * 上传人
     */

    private Integer createBy;
    /**
     * 修改人
     */

    private Integer updateBy;
    /**
     * 上传时间
     */

    private Date createTime;
    /**
     * 文件来自那个模块
     */

    private String module;
    /**
     * 修改时间
     */

    private Date updateTime;
    /**
     * 跳转链接
     */

    private String dataHref;

}
