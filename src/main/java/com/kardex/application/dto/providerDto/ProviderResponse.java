package com.kardex.application.dto.providerDto;

import lombok.Data;

@Data
public class ProviderResponse {
    private Long id;
    private String companyName;
    private String imageUrl;
    private String email;
    private String phone;
    private Boolean status;
    private String description;
}
