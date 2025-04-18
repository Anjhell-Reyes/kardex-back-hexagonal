package com.kardex.infrastructure.out.adapter;

import com.kardex.domain.exception.NotDataFoundException;
import com.kardex.domain.exception.ProviderNotFoundException;
import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Provider;
import com.kardex.domain.spi.IProviderPersistencePort;
import com.kardex.infrastructure.out.entity.ProviderEntity;
import com.kardex.infrastructure.out.mapper.ProviderEntityMapper;
import com.kardex.infrastructure.out.repository.IProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProviderJpaAdapter implements IProviderPersistencePort {

    private final IProviderRepository providerRepository;
    private final ProviderEntityMapper providerEntityMapper;

    @Override
    public Provider saveProvider(Provider provider) {
        providerRepository.save(providerEntityMapper.toEntity(provider));
        return provider;
    }

    @Override
    public Provider getProvider(Long providerId) {
        return providerEntityMapper.toProvider(providerRepository.findById(providerId).orElseThrow(ProviderNotFoundException::new));
    }

    @Override
    public CustomPage<Provider> getAllProviders(int offset, int limit, String sortBy, boolean asc) {
        Sort sort = Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(offset / limit, limit, sort);

        // Filtramos por userId
        Page<ProviderEntity> providerPage = providerRepository.findAll(pageable);

        if (providerPage.isEmpty()) {
            throw new NotDataFoundException();
        }

        List<Provider> providers = providerPage.getContent().stream()
                .map(providerEntityMapper::toProvider)
                .collect(Collectors.toList());

        return new CustomPage<>(
                providers,
                providerPage.getNumber(),
                providerPage.getSize(),
                providerPage.getTotalElements()
        );
    }

    @Override
    public List<Provider> getAll(){
        List<ProviderEntity> providerEntities = providerRepository.findAll();
        return providerEntities.stream()
                .map(providerEntityMapper::toProvider)
                .collect(Collectors.toList());
    }

    @Override
    public List<Provider> getAllProvidersName() {
        List<ProviderEntity> providerEntities = providerRepository.findAll();
        return providerEntities.stream()
                .map(providerEntityMapper::toProvider)
                .collect(Collectors.toList());
    }

    @Override
    public void updateProvider(Provider provider) {
        providerRepository.save(providerEntityMapper.toEntity(provider));
    }

    @Override
    public void deleteProvider(Long providerId) {
        providerRepository.deleteById(providerId);
    }
}
