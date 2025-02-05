package com.kardex.infrastructure.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.kardex.domain.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfiguration {
    @Bean
    public BlobServiceClient blobServiceClient(
            @Value(Constants.AZURE_CONNECTION) String connectionString) {
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    @Bean
    public BlobContainerClient blobContainerClient(BlobServiceClient blobServiceClient,
                                                   @Value(Constants.AZURE_CONTAINER_NAME) String containerName) {
        return blobServiceClient.getBlobContainerClient(containerName);
    }

}
