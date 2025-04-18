package com.kardex.application.dto.productDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kardex.domain.utils.Constants;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductUpdateRequest {

    private String name;
    private String imageUrl;

    @JsonIgnore
    private MultipartFile file;

    @PositiveOrZero(message = Constants.QUANTITY_POSITIVE_MESSAGE)
    private Integer quantity;

    @Positive(message = Constants.PRICE_POSITIVE_MESSAGE)
    private Double price;

    private String status;
    private String description;
    private Long providerId;
}
