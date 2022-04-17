package com.sndshun.library.controller;

import com.sndshun.library.utils.Result;
import com.sndshun.library.vo.UploadImgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author mr.sun
 * @Creation 2022 2022/3/23 9:55
 * @Desc:
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class UploadImgController {
    @Value("${file.staticPath}")
    private String path;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.domain}")
    private String domain;

    @PostMapping("/upload")
    public Result<UploadImgVo> uploadImg(MultipartFile file) {
        //获取yyyy
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
        String newFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + random + "." + suffix;
        log.debug("新文件名称：{}", newFileName);
        File file1 = new File(imgFolder, newFileName);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        try {
            file.transferTo(file1);
            UploadImgVo imgVo = new UploadImgVo();
            imgVo.setImgName(newFileName);
            imgVo.setImgSize(Long.toString(size));
            imgVo.setImgType(suffix);
            String imgUrl = domain+ path+year + "/" + newFileName;
            imgVo.setImgPath(imgUrl);
            log.debug("文件访问路径：{}", imgUrl);
            return Result.success(imgVo);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.success(null);
        }
    }

    @GetMapping("/delete")
    public Result<Boolean> delFile(String imgUrl) throws FileNotFoundException {
        log.debug("删除文件路径：{}", imgUrl);
        String filePath =uploadFolder+imgUrl.substring(imgUrl.indexOf(path) + path.length());
        log.debug("删除文件真实路径：{}",filePath);
        File file = new File(filePath);
        if (file.exists()) {
            //如果存在
            if (file.delete()) {
                return Result.success(true);
            } else {
                return Result.success(false);
            }
        } else {
            throw new FileNotFoundException("文件不存在");
        }

    }

}
