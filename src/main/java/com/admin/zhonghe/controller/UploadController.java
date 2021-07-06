package com.admin.zhonghe.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {
    @Value("${web.upload-path}")
    private String uploadPath;
    @RequestMapping("/upload")
    public R upload(MultipartFile file) {
        if (file.isEmpty()) {
            return R.failed("请上传相关文件");
        }
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.indexOf("."));
        String fileName = UUID.randomUUID() + extension;
        // 生成一个新的文件名
        fileName = UUID.randomUUID() + extension;
        File dest = new File(uploadPath + fileName);
        // 检测文件目录是否存在 不存在则创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            System.out.println("上传文件成功！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传文件失败！");
        }
        return R.ok("/"+fileName);

    }

}
