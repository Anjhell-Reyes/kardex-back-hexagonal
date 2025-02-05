package com.kardex.application.handler.productHandler;

import com.kardex.domain.api.IImageServicePort;
import com.kardex.domain.exception.ImageNotNullException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class AzureBlobStorageHandler implements IAzureBlobStorageHandler{

    private final IImageServicePort imageServicePort;

    @Override
    public String uploadImage(MultipartFile image) {
        try{
            return imageServicePort.uploadFile( image.getOriginalFilename(), image.getInputStream(), image.getSize());
        }catch (IOException e){
            throw new ImageNotNullException();
        }
    }

    @Override
    public boolean deleteFileByUrl(String fileUrl) {
        try {
            return imageServicePort.deleteFileByUrl(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
