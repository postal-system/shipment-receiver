package io.codero.shipmentreciver.client;

import io.codero.shipmentreciver.dto.LetterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "letter-service", url = "${spring.feign.letter-client.url}")
public interface LetterClient {
    @PostMapping("/api/letters")
    void save(@RequestBody LetterDto dto);
}
