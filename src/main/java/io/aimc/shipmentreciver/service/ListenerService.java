package io.aimc.shipmentreciver.service;

import io.aimc.shipmentreciver.model.RawShipment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListenerService {
    private final ShipmentService shipmentService;

    @KafkaListener(topics = "${spring.kafka.topic}")
    public void get(RawShipment rawShipment) {
        log.info("received: {}", rawShipment.toString());
        shipmentService.add(rawShipment);
    }
}
