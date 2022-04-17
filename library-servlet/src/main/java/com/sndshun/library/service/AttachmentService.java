package com.sndshun.library.service;


import com.sndshun.library.common.config.UploadModuleConfig;
import com.sndshun.library.entity.Attachment;
import com.sndshun.library.utils.PageUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (Attachment)表服务接口
 *
 * @author sndshun
 * @since 2022-04-15 16:35:25
 */
public interface AttachmentService {

    /**
     * 接受图片并保存到数据库
     *
     * @param file 图片文件
     * @param uploadModuleConfig 配置文件
     * @return 实例对象
     */
    Attachment saveData(MultipartFile file, UploadModuleConfig uploadModuleConfig,Integer uid);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Attachment getById(String id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param attachment 实例对象
     * @return 对象列表
     */
    List<Attachment> list(Attachment attachment);

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    void page(PageUtil<Attachment> page);

    /**
     * 新增数据
     *
     * @param attachment 实例对象
     * @return 实例对象
     */
    boolean save(Attachment attachment);

    /**
     * 修改数据
     *
     * @param attachment 实例对象
     * @return 实例对象
     */
    boolean update(Attachment attachment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(String id);



}
