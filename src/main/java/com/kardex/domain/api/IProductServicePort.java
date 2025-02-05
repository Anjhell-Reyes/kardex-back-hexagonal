package com.kardex.domain.api;

import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Product;

public interface IProductServicePort {
    Product saveProduct(Product product);

    Product getProduct(Long id);

    CustomPage<Product> getAllProducts(int page, int size, String sortBy, boolean asc);

    void updateProduct(Long productId, Product product);

    void deleteProduct(Long productId);
}
