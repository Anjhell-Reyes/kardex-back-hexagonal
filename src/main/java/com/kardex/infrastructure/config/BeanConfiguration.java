package com.kardex.infrastructure.config;

import com.azure.storage.blob.BlobContainerClient;
import com.kardex.domain.api.IImageServicePort;
import com.kardex.domain.api.IProductServicePort;
import com.kardex.domain.spi.IProductPersistencePort;
import com.kardex.domain.usecase.ProductUseCase;
import com.kardex.infrastructure.out.adapter.AzureBlobStorageAdapter;
import com.kardex.infrastructure.out.adapter.ProductJpaAdapter;
import com.kardex.infrastructure.out.mapper.ProductEntityMapper;
import com.kardex.infrastructure.out.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;
    private final BlobContainerClient blobContainerClient;

    @Bean
    public IProductPersistencePort productPersistencePort(){
        return new ProductJpaAdapter(productRepository, productEntityMapper);
    }

    @Bean
    public IProductServicePort productServicePort() {
        return new ProductUseCase(productPersistencePort());
    }

    @Bean
    public IImageServicePort imageServicePort(){return  new AzureBlobStorageAdapter(blobContainerClient);}
}
