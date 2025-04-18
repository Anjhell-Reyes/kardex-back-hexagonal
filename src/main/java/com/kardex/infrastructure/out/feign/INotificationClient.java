package com.kardex.infrastructure.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import com.kardex.infrastructure.config.FeignConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notifications-service", url = "http://localhost:8083", configuration = FeignConfiguration.class)
public interface INotificationClient {

    @PostMapping("/notifications/lowStock")
    void saveProductNotification(@RequestParam String userId, @RequestParam String productName, @RequestParam String companyName);
}
