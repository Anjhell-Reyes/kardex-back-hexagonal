package com.kardex.application.handler.providerHandler;

import com.kardex.application.dto.providerDto.*;
import com.kardex.application.handler.cloudinaryHandler.ICloudinaryHandler;
import com.kardex.application.mapper.ProviderMapper;
import com.kardex.domain.api.IProviderServicePort;
import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Provider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProviderHandler implements IProviderHandler {

    private final IProviderServicePort providerServicePort;
    private final ProviderMapper providerMapper;
    private final ICloudinaryHandler cloudinaryHandler;

    @Override
    public void saveProvider(ProviderRequest providerRequest, MultipartFile image) {
        String imageUrl = cloudinaryHandler.uploadImage(image);

        Provider provider = providerMapper.toProvider(providerRequest);
        provider.setImageUrl(imageUrl);
        providerServicePort.saveProvider(provider);
    }

    @Override
    public ProviderResponse getProvider(Long providerId) {
        Provider provider = providerServicePort.getProvider(providerId);

        return providerMapper.toResponse(provider);
    }

    @Override
    public void updateProvider(Long providerId, ProviderUpdateRequest providerRequest) {
        Provider oldProvider = providerServicePort.getProvider(providerId);

        String imageUrl = oldProvider.getImageUrl();

        if (providerRequest.getFile() != null && !providerRequest.getFile().isEmpty()) {
            if (cloudinaryHandler.deleteFileByUrl(imageUrl)) {
                imageUrl = cloudinaryHandler.uploadImage(providerRequest.getFile());
            }
        }

        Provider updatedProvider = providerMapper.toProvider(providerRequest);
        updatedProvider.setImageUrl(imageUrl);

        providerServicePort.updateProvider(providerId, updatedProvider);
    }

    @Override
    public void deleteProvider(Long providerId) {
        Provider provider = providerServicePort.getProvider(providerId);

        if (provider.getImageUrl() != null && !provider.getImageUrl().isEmpty()) {
            cloudinaryHandler.deleteFileByUrl(provider.getImageUrl());
        }
        providerServicePort.deleteProvider(providerId);
    }

    @Override
    public Page<ProviderPaginated> getAllProviders(int page, int size, String sortBy, boolean asc) {
        CustomPage<Provider> customPage = providerServicePort.getAllProviders(page, size, sortBy, asc);

        List<ProviderPaginated> paginatedProviders = customPage.getContent().stream()
                .map(providerMapper::toProviderPaginated)
                .collect(Collectors.toList());

        return new PageImpl<>(paginatedProviders, PageRequest.of(customPage.getPageNumber(), customPage.getPageSize()), customPage.getTotalElements());
    }

    @Override
    public List<ProviderNameResponse> getProviderName() {
        List<Provider> providers= providerServicePort.getProviderName();
        return providers.stream()
                .map(providerMapper::toProviderResponse)
                .collect(Collectors.toList());
    }
}
