package com.sndshun.library.controller;


import com.sndshun.library.common.config.UploadModuleConfig;
import com.sndshun.library.entity.Attachment;
import com.sndshun.library.service.AttachmentService;
import com.sndshun.library.utils.PageUtil;
import com.sndshun.library.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * (Attachment)表控制层
 *
 * @author sndshun
 * @since 2022-04-15 16:35:25
 */
@RestController
@RequestMapping("attachment")
public class AttachmentController {
    /**
     * 服务对象
     */
    @Resource
    private AttachmentService attachmentService;


    @GetMapping("page")
    public Result<PageUtil<Attachment>> page(PageUtil<Attachment> pageUtil) {
        this.attachmentService.page(pageUtil);

        return Result.success(pageUtil);
    }

    @GetMapping("{id}")
    public Result<Attachment> selectOne(@PathVariable String id) {
        return Result.success(this.attachmentService.getById(id));
    }


    @PostMapping
    public Result<Boolean> insert(@RequestBody Attachment attachment) {
        return Result.success(this.attachmentService.save(attachment));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody Attachment attachment) {
        return Result.success(this.attachmentService.update(attachment));
    }


    @DeleteMapping
    public Result<Boolean> delete(String id) {
        return Result.success(this.attachmentService.removeById(id));
    }

    @PostMapping("bookCoverUpload")
    public Result<Attachment> upload(MultipartFile file,Integer uid) {
        Attachment attachment = this.attachmentService.saveData(file, UploadModuleConfig.BOOK_COVER, uid);
        Attachment returnAttachment = new Attachment();
        returnAttachment.setId(attachment.getId());
        returnAttachment.setImgUrl(attachment.getImgUrl());
        return Result.success(returnAttachment);
    }
    @PostMapping("bookContentUpload")
    public Result<Attachment> bookContentUpload(MultipartFile file,Integer uid) {
        Attachment attachment = this.attachmentService.saveData(file, UploadModuleConfig.BOOK_CONTENT, uid);
        Attachment returnAttachment = new Attachment();
        returnAttachment.setImgUrl(attachment.getImgUrl());
        returnAttachment.setDataHref(attachment.getDataHref());
        returnAttachment.setImgDesc(attachment.getImgDesc());
        return Result.success(returnAttachment);
    }
}
