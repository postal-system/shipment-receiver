package io.aimc.shipmentreciver.client;

import io.aimc.shipmentreciver.dto.LetterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "letter-service", url = "${spring.feign.letter-client.url}")
public interface LetterClient {
    @PostMapping("/letter")
    void save(@RequestBody LetterDto dto);
}
