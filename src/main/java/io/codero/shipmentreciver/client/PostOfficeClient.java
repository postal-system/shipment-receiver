package io.codero.shipmentreciver.client;

import io.codero.shipmentreciver.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "post-office-service", url = "${spring.feign.post-office-client.url}")
public interface PostOfficeClient {
    @GetMapping("/api/post-offices/{id}")
    PersonDto getById(@PathVariable("id") Integer id);
}
