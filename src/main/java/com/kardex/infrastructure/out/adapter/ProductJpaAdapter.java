package com.kardex.infrastructure.out.adapter;

import com.kardex.domain.exception.NotDataFoundException;
import com.kardex.domain.exception.ProductNotFoundException;
import com.kardex.domain.exception.ProductNotUpdatedException;
import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Product;
import com.kardex.domain.spi.IProductPersistencePort;
import com.kardex.infrastructure.out.entity.ProductEntity;
import com.kardex.infrastructure.out.mapper.ProductEntityMapper;
import com.kardex.infrastructure.out.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductJpaAdapter implements IProductPersistencePort {

    private final IProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Product saveProduct(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
        return product;
    }

    @Override
    public Product getProduct(Long id) {
        return productEntityMapper.toProduct(productRepository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    @Override
    public CustomPage<Product> getAllProducts(String userId, int offset, int limit, String sortBy, boolean asc) {
        Sort sort = Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(offset / limit, limit, sort);

        // Filtramos por userId
        Page<ProductEntity> productPage = productRepository.findAllByUserId(userId, pageable);

        return getProductCustomPage(productPage);
    }

    @Override
    public CustomPage<Product> getAllProductsByProviderId(String userId,Long providerId, int offset, int limit, String sortBy, boolean asc) {
        Sort sort = Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(offset / limit, limit, sort);

        // Filtramos por userId
        Page<ProductEntity> productPage = productRepository.findByUserIdAndProviderId(userId, providerId, pageable);

        return getProductCustomPage(productPage);
    }

    private CustomPage<Product> getProductCustomPage(Page<ProductEntity> productPage) {
        if (productPage.isEmpty()) {
            throw new NotDataFoundException();
        }

        List<Product> products = productPage.getContent().stream()
                .map(productEntityMapper::toProduct)
                .collect(Collectors.toList());

        return new CustomPage<>(
                products,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements()
        );
    }


    @Override
    public void updateProduct(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public int updateQuantityProduct(Long productId, Integer newQuantity) {
        return productRepository.updateQuantity(productId, newQuantity);
    }
}
