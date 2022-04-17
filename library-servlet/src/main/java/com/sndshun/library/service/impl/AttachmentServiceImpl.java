package com.sndshun.library.service.impl;


import com.sndshun.library.common.config.UploadModuleConfig;
import com.sndshun.library.entity.Attachment;
import com.sndshun.library.mapper.AttachmentMapper;
import com.sndshun.library.service.AttachmentService;
import com.sndshun.library.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * (Attachment)表服务实现类
 *
 * @author sndshun
 * @since 2022-04-15 16:35:25
 */
@Slf4j
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
    @Resource
    private AttachmentMapper attachmentMapper;

    @Value("${file.staticPath}")
    private String path;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.domain}")
    private String domain;
    /**
     * 接受图片并保存到数据库
     *
     * @param file               图片文件
     * @param uploadModuleConfig 配置文件
     * @return 实例对象
     */
    @Override
    public Attachment saveData(MultipartFile file, UploadModuleConfig uploadModuleConfig,Integer uid) {
        Attachment attachment = new Attachment();
        String year = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"));
        String imgPath = uploadFolder + year + "/";
        log.debug("上传文件路径：{}", imgPath);
        File imgFolder = new File(imgPath);
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        long size = file.getSize();
        log.debug("上传文件名称：{}", filename);
        log.debug("上传文件后缀：{}", suffix);
        log.debug("上传文件大小单位字节：{}", size);
        //四位随机数
        String random = String.valueOf(Math.random()).substring(2, 5);
        log.debug("三位随机数：{}", random);
        String id=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + random;
        String newFileName =  id+ "." + suffix;
        log.debug("新文件名称：{}", newFileName);
        File file1 = new File(imgFolder, newFileName);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        try {
            String imgUrl = domain+ path+year + "/" + newFileName;
            log.debug("文件访问路径：{}", imgUrl);
            file.transferTo(file1);
            //保存到数据库
            attachment.setId(id);
            attachment.setImgName(newFileName);
            attachment.setSuffix(suffix);
            attachment.setSize(Long.toString(size));
            attachment.setImgUrl(imgUrl);
            attachment.setAbsolutePath(imgPath);
            attachment.setModule(uploadModuleConfig.getModuleName());
            attachment.setImgDesc(uploadModuleConfig.getDescription());
            attachment.setCreateBy(uid);
            attachment.setDataHref(imgUrl);
            this.attachmentMapper.insert(attachment);
            return attachment;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Attachment getById(String id) {
        return this.attachmentMapper.queryById(id);
    }

    /**
     * 通过实体类查询数据
     *
     * @param attachment 主键
     * @return 实例对象
     */
    @Override
    public List<Attachment> list(Attachment attachment) {
        return this.attachmentMapper.queryAll(attachment);
    }

    /**
     * 分页查询
     *
     * @param page 分页工具类
     */
    @Override
    public void page(PageUtil<Attachment> page) {
        page.setTotal(this.attachmentMapper.queryCount());
        List<Attachment> list = this.attachmentMapper.queryAllByLimit(page);
        page.setList(list);
    }

    /**
     * 新增数据
     *
     * @param attachment 实例对象
     * @return 实例对象
     */
    @Override
    public boolean save(Attachment attachment) {
        return this.attachmentMapper.insert(attachment) > 0;
    }

    /**
     * 修改数据
     *
     * @param attachment 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Attachment attachment) {
        return this.attachmentMapper.update(attachment) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(String id) {
        return this.attachmentMapper.deleteById(id) > 0;
    }
}
