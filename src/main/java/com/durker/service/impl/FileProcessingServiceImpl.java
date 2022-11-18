package com.durker.service.impl;

import com.durker.service.IFileProcessingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Transactional
@Service
public class FileProcessingServiceImpl implements IFileProcessingService {

    @Value("${file.uploadPath}")
    private String uploadPath;

    @Value("${file.server}")
    private String fileServer;

    @Override
    public String upload(MultipartFile file, String folder) throws Exception {
        String picUrl = null;
        if (file != null && !file.isEmpty()) {
            // 如果上传的路径不存在，创建相应的文件夹。
            File fileDirectory = new File(uploadPath + folder);
            if (!fileDirectory.isDirectory()) {
                fileDirectory.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1); // 获取扩展名

            String fileName = UUID.randomUUID().toString().replace("-", "") + "." + extension; // 自定义文件名

            picUrl = folder + (folder.equals("") ? "" : "/") + fileName;

            String pathname = uploadPath + File.separator + picUrl;
            File uploadFile = new File(pathname);

            file.transferTo(uploadFile); // 写入文件
            //设置权限
            Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            perms.add(PosixFilePermission.GROUP_READ);
            perms.add(PosixFilePermission.OTHERS_READ);
            Files.setPosixFilePermissions(Paths.get(pathname), perms);

        }
        return picUrl;
    }

    @Override
    public String webServerHttpUrl() {
        return fileServer;
    }
}
