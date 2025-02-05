package com.kardex.infrastructure.out.adapter;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.kardex.domain.api.IImageServicePort;
import com.kardex.domain.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
public class AzureBlobStorageAdapter implements IImageServicePort {

    private final BlobContainerClient blobContainerClient;

    @Override
    public String uploadFile(String fileName, InputStream inputStream, long fileSize) {
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        BlobHttpHeaders headers = new BlobHttpHeaders();

        // Verificar si el archivo ya existe
        if (blobClient.exists()) {
            // Generar un nombre único si ya existe
            fileName = UUID.randomUUID().toString() + Constants.FILE_NAME_SEPARATOR + fileName;
            blobClient = blobContainerClient.getBlobClient(fileName);
        }

        // Obtener extensión del archivo y asignar el Content-Type
        String fileExtension = getFileExtension(fileName);
        String contentType = getContentType(fileExtension);
        headers.setContentType(contentType);

        // Subir el archivo
        blobClient.upload(inputStream, fileSize, true);
        blobClient.setHttpHeaders(headers);

        return blobClient.getBlobUrl();
    }

    @Override
    public Boolean deleteFileByUrl(String fileUrl) {
        String fileName = extractFileName(fileUrl);

        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);

        if (blobClient.exists()) {
            blobClient.delete();
            return true;
        }else {
            return false;
        }
    }

    private String getContentType(String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            default:
                return "application/octet-stream"; // Valor por defecto para otros tipos
        }
    }

    private String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            return fileName.substring(index + 1);
        }
        return "";
    }

    // Método para extraer el nombre del archivo desde la URL
    private String extractFileName(String fileUrl) {
        return fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
    }

}
