package com.kardex.application.dto.providerDto;

import com.kardex.domain.utils.Constants;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProviderRequest {

    @NotBlank(message = Constants.COMPANY_NOT_BLANK_MESSAGE)
    private String companyName;

    @NotBlank(message = Constants.EMAIL_NOT_BLANK_MESSAGE)
    @Email(message = Constants.EMAIL_VALID_FORMAT_MESSAGE)
    private String email;

    @NotBlank(message = Constants.PHONE_NOT_BLANK_MESSAGE)
    @Size(max = Constants.MAX_LENGHT_PHONE,message = Constants.PHONE_SIZE_MESSAGE)
        private String phone;

    @NotBlank(message = Constants.STATUS_NOT_NULL_MESSAGE)
    private String status;

    @NotBlank(message = Constants.DESCRIPTION_NOT_BLANK_MESSAGE)
    private String description;
}
