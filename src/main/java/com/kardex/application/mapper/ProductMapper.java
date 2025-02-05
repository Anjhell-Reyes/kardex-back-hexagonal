package com.kardex.application.mapper;

import com.kardex.application.dto.productDto.ProductPaginated;
import com.kardex.application.dto.productDto.ProductRequest;
import com.kardex.application.dto.productDto.ProductResponse;
import com.kardex.application.dto.productDto.ProductUpdateRequest;
import com.kardex.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product toProduct(ProductRequest productRequest);

    Product toProduct(ProductUpdateRequest productRequest);

    ProductResponse toResponse(Product product);

    ProductPaginated toProductPaginated(Product product);
}
