package com.kardex.application.dto.providerDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProviderSummaryResponse {
    private Long id;
    private String companyName;
    private String email;
}
