package com.kardex.infrastructure.out.mapper;

import com.kardex.domain.model.Provider;
import com.kardex.infrastructure.out.entity.ProviderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProviderEntityMapper {

    ProviderEntity toEntity(Provider provider);

    Provider toProvider(ProviderEntity providerEntity);
}
