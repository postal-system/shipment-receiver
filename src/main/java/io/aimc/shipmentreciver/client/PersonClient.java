package io.aimc.shipmentreciver.client;

import io.aimc.shipmentreciver.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${spring.feign.person-client.name}",
        url = "${spring.feign.person-client.url}")
public interface PersonClient {
    @GetMapping("/api/persons/{id}")
    PersonDto getById(@PathVariable("id") Integer id);
}
