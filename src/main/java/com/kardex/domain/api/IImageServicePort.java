package com.kardex.domain.api;

import java.io.InputStream;

public interface IImageServicePort {
    String uploadFile(String originalFilename, InputStream inputStream, long size);

    Boolean deleteFileByUrl(String fileUrl);
}
