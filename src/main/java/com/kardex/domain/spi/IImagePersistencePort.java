package com.kardex.domain.spi;

public interface IImagePersistencePort {

    Boolean deleteFileByUrl(String publicId);

    String uploadFile(byte[] file);
}
