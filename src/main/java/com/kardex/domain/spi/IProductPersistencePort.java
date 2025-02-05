package com.kardex.domain.spi;

import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Product;

public interface IProductPersistencePort {
    Product saveProduct(Product product);

    Product getProduct(Long id);

    CustomPage<Product> getAllProducts(int offset, int limit, String sortBy, boolean asc);

    void updateProduct(Product product);

    void deleteProduct(Long productId);
}
