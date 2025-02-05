package com.kardex.domain.usecase;

import com.kardex.domain.api.IProductServicePort;
import com.kardex.domain.exception.*;
import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Product;
import com.kardex.domain.spi.IProductPersistencePort;

public class ProductUseCase implements IProductServicePort {

    private final IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public Product saveProduct(Product product) {

        if(product.getName() == null || product.getName().isEmpty()){
            throw new NameNotNullException();
        }
        if(product.getQuantity() == null){
            product.setQuantity(0);
        }
        if(product.getImageUrl() == null || product.getImageUrl().isEmpty()){
            throw new ImageNotNullException();
        }
        if(product.getPrice() == null){
            throw new PriceNotNullException();
        }
        if (product.getStatus() == null){
            throw new StatusNotNullException();
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()){
            throw new DescriptionNotNullException();
        }

        return productPersistencePort.saveProduct(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productPersistencePort.getProduct(id);
    }

    @Override
    public CustomPage<Product> getAllProducts(int page, int size, String sortBy, boolean asc) {
        if(page < 0){
            throw new InvalidPageIndexException();
        }
        int offset =page * size;

        return productPersistencePort.getAllProducts(offset, size, sortBy, asc);
    }

    @Override
    public void updateProduct(Long productId, Product product) {
        Product oldProduct = productPersistencePort.getProduct(productId);

        product.setId(oldProduct.getId());
        product.setName(copyIfNull(product.getName(), oldProduct.getName()));
        product.setImageUrl(copyIfNull(product.getImageUrl(), oldProduct.getImageUrl()));
        product.setPrice(copyIfNull(product.getPrice(), oldProduct.getPrice()));
        product.setDescription(copyIfNull(product.getDescription(), oldProduct.getDescription()));
        product.setStatus(copyIfNull(product.getStatus(), oldProduct.getStatus()));
        product.setQuantity(copyIfNull(product.getQuantity(), oldProduct.getQuantity()));

        productPersistencePort.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productPersistencePort.getProduct(productId);

        if(product != null){
            productPersistencePort.deleteProduct(productId);
        }
    }

    private <T> T copyIfNull(T requestValue, T oldValue) {
        return (requestValue == null) ? oldValue : requestValue;
    }
}
