package com.kardex.domain.usecase;

import com.kardex.domain.api.IProductServicePort;
import com.kardex.domain.exception.*;
import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Product;
import com.kardex.domain.model.Provider;
import com.kardex.domain.spi.INotificationPersistencePort;
import com.kardex.domain.spi.IProductPersistencePort;
import com.kardex.domain.spi.IProviderPersistencePort;
import com.kardex.domain.utils.Constants;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductUseCase implements IProductServicePort {

    private final IProductPersistencePort productPersistencePort;
    private final IProviderPersistencePort providerPersistencePort;
    private final INotificationPersistencePort notificationPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort, IProviderPersistencePort providerPersistencePort, INotificationPersistencePort notificationPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.providerPersistencePort = providerPersistencePort;
        this.notificationPersistencePort = notificationPersistencePort;
    }

    @Override
    public Product saveProduct(Product product) {

        if(product.getQuantity() == null){
            product.setQuantity(0);
        }
        if(product.getImageUrl() == null || product.getImageUrl().isEmpty()){
            throw new ImageNotNullException();
        }
        if(product.getPrice() == null){
            throw new PriceNotNullException();
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()){
            throw new DescriptionNotNullException();
        }
        if(product.getName() == null || product.getName().isEmpty()){
            throw new NameNotNullException();
        }
        if(product.getUserId() == null){
            throw new UserIdNotNullException();
        }

        product.setStatus(product.getQuantity() > 0);
        product.setName(product.getName().toLowerCase());

        validateProviders(product);

        return productPersistencePort.saveProduct(product);
    }

    @Override
    public Product getProduct(String userId, Long productId) {
        Product product = productPersistencePort.getProduct(productId);

        if(product.getUserId().equals(userId) || Objects.equals(userId, Constants.ORDER_SERVICE)){
            return product;
        }else {
            throw new UserForbiddenException();
        }
    }

    @Override
    public CustomPage<Product> getAllProducts(String userId, int page, int size, String sortBy, boolean asc) {
        if(page < 0){
            throw new InvalidPageIndexException();
        }
        int offset =page * size;
        String sortByField = getSortByField(sortBy);
        return productPersistencePort.getAllProducts(userId, offset, size, sortByField, asc);
    }

    @Override
    public CustomPage<Product> getAllProductsByProviderId(String userId, Long providerId, int page, int size, String sortBy, boolean asc) {
        if(page < 0){
            throw new InvalidPageIndexException();
        }
        int offset =page * size;
        String sortByField = getSortByField(sortBy);
        return productPersistencePort.getAllProductsByProviderId(userId, providerId, offset, size, sortByField, asc);
    }

    @Override
    public void updateProduct(Long productId, Product product) {
        Product oldProduct = productPersistencePort.getProduct(productId);
        Provider provider = (product.getProvider() != null && product.getProvider().getId() != null)
                ? providerPersistencePort.getProvider(product.getProvider().getId())
                : oldProduct.getProvider();

        product.setId(copyIfNull(product.getId(), oldProduct.getId()));
        product.setName(copyIfNull(product.getName(), oldProduct.getName()));
        product.setImageUrl(copyIfNull(product.getImageUrl(), oldProduct.getImageUrl()));
        product.setPrice(copyIfNull(product.getPrice(), oldProduct.getPrice()));
        product.setDescription(copyIfNull(product.getDescription(), oldProduct.getDescription()));
        product.setStatus(oldProduct.getStatus());
        product.setQuantity(copyIfNull(product.getQuantity(), oldProduct.getQuantity()));
        product.setProvider(provider);

        productPersistencePort.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productPersistencePort.getProduct(productId);

        if(product != null){
            productPersistencePort.deleteProduct(productId);
        }
    }

    @Override
    public void updateQuantityProduct(String userId, Long productId, Integer productQuantity) {
        Product product = productPersistencePort.getProduct(productId);

        if(product.getUserId().equals(userId) || Objects.equals(userId, Constants.ORDER_SERVICE)){

            Integer newQuantity = productQuantity + product.getQuantity();

            int updatedRows = productPersistencePort.updateQuantityProduct(productId, newQuantity);
            if (updatedRows == 0) {
                throw new ProductNotUpdatedException();
            }

            if (newQuantity <= Constants.LOW_STOCK_THRESHOLD) {
                notificationPersistencePort.sendNotification(userId, product.getName(), product.getProvider().getCompanyName());
            }
        }else{
            throw new UserForbiddenException();
        }
    }

    public void validateProviders(Product product) {
        Long providerId = product.getProvider().getId();
        Provider existingProvider;

        if (providerId == null) {
            throw new AvailableProvidersException(getAvailableProviders());
        }

        try {
            existingProvider = providerPersistencePort.getProvider(providerId);
        } catch (ProviderNotFoundException e) {
            throw new AvailableProvidersException(getAvailableProviders());
        }

        product.setProvider(existingProvider);
    }

    public List<String> getAvailableProviders() {
        return providerPersistencePort.getAll().stream()
                .map(provider -> provider.getId() + Constants.SEPARATOR_1 + provider.getCompanyName())
                .collect(Collectors.toList());
    }

    private <T> T copyIfNull(T requestValue, T oldValue) {
        return (requestValue == null) ? oldValue : requestValue;
    }

    public String getSortByField(String sortBy) {
        return switch (sortBy) {
            case "provider" -> Constants.SORT_BY_PROVIDER_NAME;
            default -> Constants.DEFAULT_SORT_BY;
        };
    }
}
