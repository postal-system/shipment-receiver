package io.aimc.shipmentreciver.client;

import io.aimc.shipmentreciver.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${spring.feign.user-client.url}")
public interface UserClient {
    @GetMapping("/users/{id}")
    PersonDto getById(@PathVariable("id") Integer id);
}