package io.aimc.shipmentreciver.client;

import io.aimc.shipmentreciver.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "person-service", url = "${spring.feign.person-client.url}")
public interface PersonClient {
    @GetMapping("/person/{id}")
    PersonDto getById(@PathVariable("id") Integer id);
}
