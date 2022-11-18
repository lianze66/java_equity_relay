package com.durker.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileProcessingService {

    String upload(MultipartFile file, String folder) throws Exception;

    String webServerHttpUrl();

}
