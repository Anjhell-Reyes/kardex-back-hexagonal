package com.kardex.application.handler.productHandler;

import org.springframework.web.multipart.MultipartFile;

public interface IAzureBlobStorageHandler {
    String uploadImage(MultipartFile image);

    boolean deleteFileByUrl(String fileUrl);
}
