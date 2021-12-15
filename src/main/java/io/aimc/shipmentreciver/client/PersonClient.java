package io.aimc.shipmentreciver.client;

import io.aimc.shipmentreciver.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${spring.feign.client.name}", url = "${spring.feign.client.url}")
public interface PersonClient {
    @GetMapping("/api/persons/{id}")
    Person getById(@PathVariable Integer id);
}