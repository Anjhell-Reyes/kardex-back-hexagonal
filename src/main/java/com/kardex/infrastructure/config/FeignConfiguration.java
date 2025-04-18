package com.kardex.infrastructure.config;

import com.kardex.infrastructure.exceptionHandler.FeignErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {
    @Bean
    public FeignErrorDecoder feignErrorDecoder() {
        return new FeignErrorDecoder();
    }
}
