package com.kardex.domain.spi;

import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Product;

public interface IProductPersistencePort {
    Product saveProduct(Product product);

    Product getProduct(Long id);

    CustomPage<Product> getAllProducts(String userId, int offset, int limit, String sortBy, boolean asc);

    void updateProduct(Product product);

    void deleteProduct(Long productId);

    int updateQuantityProduct(Long productId, Integer newQuantity);

    CustomPage<Product> getAllProductsByProviderId(String userId, Long providerId, int offset, int size, String sortByField, boolean asc);
}
