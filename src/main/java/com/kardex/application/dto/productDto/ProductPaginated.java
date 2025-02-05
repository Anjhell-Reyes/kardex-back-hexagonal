package com.kardex.application.dto.productDto;

import lombok.Data;

@Data
public class ProductPaginated {
    private Long id;
    private String name;
    private String imageUrl;
    private Integer quantity;
    private Double price;
    private Boolean status;
    private String description;
}
