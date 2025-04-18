package com.kardex.application.handler.providerHandler;

import com.kardex.application.dto.providerDto.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProviderHandler {
    void saveProvider(ProviderRequest providerRequest, MultipartFile image);

    ProviderResponse getProvider(Long id);

    void updateProvider(Long providerId, ProviderUpdateRequest providerRequest);

    void deleteProvider(Long providerId);

    Page<ProviderPaginated> getAllProviders(int page, int size, String sortBy, boolean asc);

    List<ProviderNameResponse> getProviderName();
}
