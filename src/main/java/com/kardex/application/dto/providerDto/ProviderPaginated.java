package com.kardex.application.dto.providerDto;

import lombok.Data;

@Data
public class ProviderPaginated {
    private Long id;
    private String companyName;
    private String imageUrl;
    private String email;
    private String phone;
    private Boolean status;
    private String description;
}
