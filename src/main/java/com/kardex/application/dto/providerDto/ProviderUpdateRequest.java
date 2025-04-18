package com.kardex.application.dto.providerDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kardex.domain.utils.Constants;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProviderUpdateRequest {

    private String companyName;
    private String imageUrl;

    @JsonIgnore
    private MultipartFile file;

    private String email;

    @Size(max = Constants.MAX_LENGHT_PHONE,message = Constants.PHONE_SIZE_MESSAGE)
    private String phone;

    private String status;
    private String description;
}
