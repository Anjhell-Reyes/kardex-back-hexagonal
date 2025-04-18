package com.kardex.application.dto.productDto;

import com.kardex.domain.utils.Constants;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = Constants.NAME_NOT_BLANK_MESSAGE)
    private String name;

    @PositiveOrZero(message = Constants.QUANTITY_POSITIVE_MESSAGE)
    private Integer quantity;

    @NotNull(message = Constants.PRICE_NOT_NULL_MESSAGE)
    @Positive(message = Constants.PRICE_POSITIVE_MESSAGE)
    private Double price;

    @NotBlank(message = Constants.DESCRIPTION_NOT_BLANK_MESSAGE)
    private String description;

    @NotNull(message = Constants.PROVIDER_NOT_NULL_MESSAGE)
    private Long providerId;

}
