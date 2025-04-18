package com.kardex.infrastructure.config;


import com.cloudinary.Cloudinary;
import com.kardex.domain.spi.IImagePersistencePort;
import com.kardex.domain.api.IProductServicePort;
import com.kardex.domain.api.IProviderServicePort;
import com.kardex.domain.spi.INotificationPersistencePort;
import com.kardex.domain.spi.IProductPersistencePort;
import com.kardex.domain.spi.IProviderPersistencePort;
import com.kardex.domain.usecase.ProductUseCase;
import com.kardex.domain.usecase.ProviderUseCase;
import com.kardex.infrastructure.out.adapter.NotificationServiceAdapter;
import com.kardex.infrastructure.out.adapter.ProductJpaAdapter;
import com.kardex.infrastructure.out.adapter.ProviderJpaAdapter;
import com.kardex.infrastructure.out.adapter.CloudinaryAdapter;
import com.kardex.infrastructure.out.feign.INotificationClient;
import com.kardex.infrastructure.out.mapper.ProductEntityMapper;
import com.kardex.infrastructure.out.mapper.ProviderEntityMapper;
import com.kardex.infrastructure.out.repository.IProductRepository;
import com.kardex.infrastructure.out.repository.IProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;
    private final IProviderRepository providerRepository;
    private final ProviderEntityMapper providerEntityMapper;
    private final INotificationClient notificationClient;
    private final Cloudinary cloudinary;


    @Bean
    public IProductPersistencePort productPersistencePort(){
        return new ProductJpaAdapter(productRepository, productEntityMapper);
    }

    @Bean
    public IProductServicePort productServicePort() {
        return new ProductUseCase(productPersistencePort(), providerPersistencePort(), notificationPersistencePort());
    }

    @Bean
    public IProviderPersistencePort providerPersistencePort(){
        return new ProviderJpaAdapter(providerRepository, providerEntityMapper);
    }

    @Bean
    public INotificationPersistencePort notificationPersistencePort(){
        return new NotificationServiceAdapter(notificationClient);
    }

    @Bean
    public IProviderServicePort providerServicePort() {
        return new ProviderUseCase(providerPersistencePort());
    }

    @Bean
    public IImagePersistencePort imagePersistencePort() {
        return new CloudinaryAdapter(cloudinary);
    }
}
