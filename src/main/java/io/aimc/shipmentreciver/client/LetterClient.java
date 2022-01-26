package io.aimc.shipmentreciver.client;

import io.aimc.shipmentreciver.dto.LetterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "${spring.feign.letter-client.name}", url = "${spring.feign.letter-client.url}")
public interface LetterClient {
    @PostMapping("/letter")
    public UUID save(@RequestBody LetterDto dto);
}
