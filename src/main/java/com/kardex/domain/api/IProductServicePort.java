package com.kardex.domain.api;

import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Product;

public interface IProductServicePort {
    Product saveProduct(Product product);

    Product getProduct(String userId, Long productId);

    CustomPage<Product> getAllProducts(String userId, int page, int size, String sortBy, boolean asc);

    void updateProduct(Long productId, Product product);

    void deleteProduct(Long productId);

    void updateQuantityProduct(String userId, Long productId, Integer productQuantity);

    CustomPage<Product> getAllProductsByProviderId(String userId, Long providerId, int page, int size, String sortBy, boolean asc);
}
