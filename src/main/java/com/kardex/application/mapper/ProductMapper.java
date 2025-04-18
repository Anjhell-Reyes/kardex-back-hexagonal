package com.kardex.application.mapper;

import com.kardex.application.dto.productDto.ProductPaginated;
import com.kardex.application.dto.productDto.ProductRequest;
import com.kardex.application.dto.productDto.ProductResponse;
import com.kardex.application.dto.productDto.ProductUpdateRequest;
import com.kardex.application.dto.providerDto.ProviderSummaryResponse;
import com.kardex.domain.model.Product;
import com.kardex.domain.model.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(source = "providerId", target = "provider.id")
    Product toProduct(ProductRequest productRequest);

    @Mapping(source = "providerId", target = "provider.id")
    Product toProduct(ProductUpdateRequest productRequest);

    @Mapping(source = "provider", target = "provider", qualifiedByName = "toProviderSummaryResponse")
    ProductResponse toResponse(Product product);

    @Mapping(source = "provider", target = "provider", qualifiedByName = "toProviderSummaryResponse")
    ProductPaginated toProductPaginated(Product product);

    @Named("toProviderSummaryResponse")
    default ProviderSummaryResponse toProviderSummaryResponse(Provider provider) {
        return new ProviderSummaryResponse(provider.getId(), provider.getCompanyName(), provider.getEmail());
    }
}
