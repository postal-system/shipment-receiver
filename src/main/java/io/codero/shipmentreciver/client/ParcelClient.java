package io.codero.shipmentreciver.client;

import io.codero.shipmentreciver.dto.ParcelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "parcel-service", url = "${spring.feign.parcel-client.url}")
public interface ParcelClient {
    @PostMapping("/api/parcels")
    void save(@RequestBody ParcelDto dto);
}
