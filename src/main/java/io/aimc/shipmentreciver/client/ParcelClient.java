package io.aimc.shipmentreciver.client;

import io.aimc.shipmentreciver.dto.ParcelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "${spring.feign.parcel-client.name}", url = "${spring.feign.parcel-client.url}")
public interface ParcelClient {
    @PostMapping("/parcel")
    public UUID save(@RequestBody ParcelDto dto);
}
