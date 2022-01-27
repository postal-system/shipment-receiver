package io.aimc.shipmentreciver.client;

import io.aimc.shipmentreciver.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${spring.feign.post-office-client.name}",
        url = "${spring.feign.post-office-client.url}")
public interface PostOficeClient {
    @GetMapping("/post-office/{id}")
    PersonDto getById(@PathVariable("id") Integer id);
}
