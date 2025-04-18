package com.kardex.application.handler.productHandler;

import com.kardex.application.dto.productDto.ProductPaginated;
import com.kardex.application.dto.productDto.ProductRequest;
import com.kardex.application.dto.productDto.ProductResponse;
import com.kardex.application.dto.productDto.ProductUpdateRequest;
import com.kardex.application.handler.cloudinaryHandler.ICloudinaryHandler;
import com.kardex.application.mapper.ProductMapper;
import com.kardex.domain.api.IProductServicePort;
import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductHandler implements IProductHandler {

    private final IProductServicePort productServicePort;
    private final ProductMapper productMapper;
    private final ICloudinaryHandler cloudinaryHandler;

    private String getAuthenticatedUserId() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void saveProduct(ProductRequest productRequest, MultipartFile image) {
        String userId = getAuthenticatedUserId();
        String imageUrl = cloudinaryHandler.uploadImage(image);

        Product product = productMapper.toProduct(productRequest);
        product.setImageUrl(imageUrl);
        product.setUserId(userId);
        productServicePort.saveProduct(product);
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        String userId = getAuthenticatedUserId();
        Product product = productServicePort.getProduct(userId, productId);

        return productMapper.toResponse(product);
    }

    @Override
    public Page<ProductPaginated> getAllProducts(int page, int size, String sortBy, boolean asc) {
        String userId = getAuthenticatedUserId();
        CustomPage<Product> customPage = productServicePort.getAllProducts(userId, page, size, sortBy, asc);

        List<ProductPaginated> paginatedProducts = customPage.getContent().stream()
                .map(productMapper::toProductPaginated)
                .collect(Collectors.toList());

        return new PageImpl<>(paginatedProducts, PageRequest.of(customPage.getPageNumber(), customPage.getPageSize()), customPage.getTotalElements());
    }

    @Override
    public Page<ProductPaginated> getAllProductsByProviderId(Long providerId,int page, int size, String sortBy, boolean asc) {
        String userId = getAuthenticatedUserId();
        CustomPage<Product> customPage = productServicePort.getAllProductsByProviderId(userId, providerId, page, size, sortBy, asc);

        List<ProductPaginated> paginatedProducts = customPage.getContent().stream()
                .map(productMapper::toProductPaginated)
                .collect(Collectors.toList());

        return new PageImpl<>(paginatedProducts, PageRequest.of(customPage.getPageNumber(), customPage.getPageSize()), customPage.getTotalElements());
    }

    @Override
    public void updateProduct(Long productId, ProductUpdateRequest productRequest) {
        String userId = getAuthenticatedUserId();
        Product oldProduct = productServicePort.getProduct(userId, productId);

        String imageUrl = oldProduct.getImageUrl();

        if (productRequest.getFile() != null && !productRequest.getFile().isEmpty()) {
            if (cloudinaryHandler.deleteFileByUrl(imageUrl)) {
                imageUrl = cloudinaryHandler.uploadImage(productRequest.getFile());
            }
        }

        Product updatedProduct = productMapper.toProduct(productRequest);
        updatedProduct.setImageUrl(imageUrl);
        updatedProduct.setUserId(userId);

        productServicePort.updateProduct(productId, updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        String userId = getAuthenticatedUserId();
        Product product = productServicePort.getProduct(userId, productId);

        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            cloudinaryHandler.deleteFileByUrl(product.getImageUrl());
        }
        productServicePort.deleteProduct(productId);
    }

    @Override
    public void updateQuantityProduct(Long productId, Integer productQuantity) {
        String userId = getAuthenticatedUserId();
        productServicePort.updateQuantityProduct(userId, productId, productQuantity);
    }
}
