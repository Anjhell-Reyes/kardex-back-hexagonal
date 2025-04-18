package com.kardex.domain.api;

import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Provider;

import java.util.List;

public interface IProviderServicePort {
    Provider saveProvider(Provider provider);

    Provider getProvider(Long providerId);

    CustomPage<Provider> getAllProviders(int page, int size, String sortBy, boolean asc);

    void updateProvider(Long providerId, Provider updatedProvider);

    void deleteProvider(Long providerId);

    List<Provider> getProviderName();
}
