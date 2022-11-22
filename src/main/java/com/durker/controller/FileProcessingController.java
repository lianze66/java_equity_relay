package com.durker.controller;

import com.durker.service.IFileProcessingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件处理接口")
@RestController
@RequestMapping("fileProcessing")
public class FileProcessingController {

    @Autowired
    private IFileProcessingService fileProcessingService;

    @ApiOperation(value = "上传", notes = "文件上传接口，单个文件最大支持100MB。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件对象", dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "folder", value = "文件夹", dataTypeClass = String.class)
    })
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam String folder) throws Exception {
        return fileProcessingService.upload(file, folder);
    }

    @ApiOperation(value = "web路径", notes = "web路径接口。")
    @GetMapping("webServerHttpUrl")
    public String webServerHttpUrl() {
        return fileProcessingService.webServerHttpUrl();
    }

//    @ApiOperation(value = "上传（默认路径）", notes = "文件上传接口，单个文件最大支持100MB。")
//    @RequestMapping(value = "upload", method = RequestMethod.POST)
//    public String upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
//        return fileProcessingService.upload(file, "");
//    }
}