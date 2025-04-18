package com.kardex.application.handler.productHandler;


import com.kardex.application.dto.productDto.ProductPaginated;
import com.kardex.application.dto.productDto.ProductRequest;
import com.kardex.application.dto.productDto.ProductResponse;
import com.kardex.application.dto.productDto.ProductUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface IProductHandler {

    void saveProduct(ProductRequest productRequest, MultipartFile image);

    ProductResponse getProduct(Long id);

    void updateProduct(Long productId, ProductUpdateRequest productRequest);

    void deleteProduct(Long productId);

    Page<ProductPaginated> getAllProducts(int page, int size, String sortBy, boolean asc);

    void updateQuantityProduct(Long productId, Integer productQuantity);

    Page<ProductPaginated> getAllProductsByProviderId(Long providerId, int page, int size, String sortBy, boolean asc);
}
