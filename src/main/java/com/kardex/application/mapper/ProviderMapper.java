package com.kardex.application.mapper;

import com.kardex.application.dto.providerDto.*;
import com.kardex.domain.model.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProviderMapper {
    Provider toProvider(ProviderRequest providerRequest);

    Provider toProvider(ProviderUpdateRequest providerRequest);

    ProviderResponse toResponse(Provider provider);

    ProviderPaginated toProviderPaginated(Provider provider);

    ProviderNameResponse toProviderResponse(Provider provider);
}
