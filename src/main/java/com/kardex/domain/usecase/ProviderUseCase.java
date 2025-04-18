package com.kardex.domain.usecase;

import com.kardex.domain.api.IProviderServicePort;
import com.kardex.domain.exception.*;
import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Provider;
import com.kardex.domain.spi.IProviderPersistencePort;
import com.kardex.domain.utils.Constants;

import java.util.List;

public class ProviderUseCase implements IProviderServicePort {

    private final IProviderPersistencePort providerPersistencePort;

    public ProviderUseCase(IProviderPersistencePort providerPersistencePort) {
        this.providerPersistencePort = providerPersistencePort;
    }

    @Override
    public Provider saveProvider(Provider provider) {

        if(provider.getCompanyName() == null || provider.getCompanyName().isEmpty()){
            throw new CompanyNotNullException();
        }
        if(provider.getImageUrl() == null || provider.getImageUrl().isEmpty()){
            throw new ImageNotNullException();
        }
        if(provider.getEmail() == null || provider.getEmail().isEmpty()){
            throw new EmailNotNullException();
        }
        if(provider.getPhone() == null || provider.getPhone().isEmpty()){
            throw new PhoneNotNullException();
        }
        if (provider.getStatus() == null){
            throw new StatusNotNullException();
        }
        if (provider.getDescription() == null || provider.getDescription().isEmpty()){
            throw new DescriptionNotNullException();
        }
        if (!isValidEmail(provider.getEmail())) {
            throw new InvalidEmailFormatException();
        }
        if(provider.getPhone().length() > Constants.MAX_LENGHT_PHONE){
            throw new PhoneMaxCharactersException();
        }

        provider.setCompanyName(provider.getCompanyName().toLowerCase());

        return providerPersistencePort.saveProvider(provider);
    }

    @Override
    public Provider getProvider(Long providerId) {
        return providerPersistencePort.getProvider(providerId);
    }

    @Override
    public CustomPage<Provider> getAllProviders(int page, int size, String sortBy, boolean asc) {
        if(page < 0){
            throw new InvalidPageIndexException();
        }
        int offset =page * size;
        return providerPersistencePort.getAllProviders(offset, size, sortBy, asc);
    }

    @Override
    public void updateProvider(Long providerId, Provider provider) {
        Provider oldProvider = providerPersistencePort.getProvider(providerId);

        provider.setId(oldProvider.getId());
        provider.setCompanyName(copyIfNull(provider.getCompanyName(), oldProvider.getCompanyName()));
        provider.setImageUrl(copyIfNull(provider.getImageUrl(), oldProvider.getImageUrl()));
        provider.setEmail(copyIfNull(provider.getEmail(), oldProvider.getEmail()));
        provider.setPhone(copyIfNull(provider.getPhone(), oldProvider.getPhone()));
        provider.setStatus(copyIfNull(provider.getStatus(), oldProvider.getStatus()));
        provider.setDescription(copyIfNull(provider.getDescription(), oldProvider.getDescription()));

        providerPersistencePort.updateProvider(provider);
    }

    @Override
    public void deleteProvider(Long providerId) {
        Provider provider = providerPersistencePort.getProvider(providerId);

        if(provider != null){
            providerPersistencePort.deleteProvider(providerId);
        }
    }

    @Override
    public List<Provider> getProviderName() {
        List<Provider> providers = providerPersistencePort.getAllProvidersName();
        if (providers.isEmpty()) {
            throw new ProviderNotFoundException();
        }
        return providers;
    }

    private <T> T copyIfNull(T requestValue, T oldValue) {
        return (requestValue == null) ? oldValue : requestValue;
    }

    private boolean isValidEmail(String email) {
        return Constants.EMAIL_PATTERN.matcher(email).matches();
    }
}
