package com.kardex.application.dto.productDto;

import com.kardex.application.dto.providerDto.ProviderSummaryResponse;
import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String userId;
    private String name;
    private String imageUrl;
    private Integer quantity;
    private Double price;
    private Boolean status;
    private String description;
    private ProviderSummaryResponse provider;
}
