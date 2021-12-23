package io.aimc.shipmentreciver.service;

import io.aimc.shipmentreciver.dto.RawShipmentDto;
import io.aimc.shipmentreciver.facade.ShipmentFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShipmentListenerService {
    private final ShipmentFacade shipmentFacade;


    @KafkaListener(topics = "${spring.kafka.shipment-topic}")
    public void getShipment(RawShipmentDto rawShipmentDto) {
        log.info("received: {}", rawShipmentDto.toString());
        shipmentFacade.add(rawShipmentDto);
    }
}
