package com.kardex.infrastructure.out.adapter;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kardex.domain.spi.IImagePersistencePort;
import com.kardex.domain.exception.UploadImageCloudinaryException;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class CloudinaryAdapter implements IImagePersistencePort {

    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(byte[] file) {
        try {
            Map<String, String> uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return uploadResult.get("url");
        }catch (Exception e){
            throw new UploadImageCloudinaryException();
        }
    }

    @Override
    public Boolean deleteFileByUrl(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
