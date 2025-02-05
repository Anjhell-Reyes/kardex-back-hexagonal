package com.kardex.infrastructure.out.mapper;

import com.kardex.domain.model.Product;
import com.kardex.infrastructure.out.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductEntityMapper {

    ProductEntity toEntity(Product product);

    Product toProduct(ProductEntity productEntity);
}
