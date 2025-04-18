package com.kardex.domain.spi;

import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Provider;

import java.util.List;

public interface IProviderPersistencePort {
    Provider saveProvider(Provider provider);

    Provider getProvider(Long providerId);

    CustomPage<Provider> getAllProviders(int offset, int limit, String sortBy, boolean asc);

    void updateProvider(Provider provider);

    void deleteProvider(Long providerId);

    List<Provider> getAll();

    List<Provider> getAllProvidersName();
}
