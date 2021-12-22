package io.aimc.shipmentreciver.service;

import feign.FeignException;
import io.aimc.shipmentreciver.client.PersonClient;
import io.aimc.shipmentreciver.dto.PersonDto;
import io.aimc.shipmentreciver.dto.RawShipmentDto;
import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.mapper.ShipmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListenerService {
    private final ShipmentService shipmentService;
    private final ShipmentMapper shipmentMapper;
    private final PersonClient personClient;

    @KafkaListener(topics = "${spring.kafka.topic}")
    public void getShipment(RawShipmentDto rawShipmentDto) {
        log.info("received: {}", rawShipmentDto.toString());
        try {
            PersonDto personDto = personClient.getById(rawShipmentDto.getIdReceiver());
            Shipment shipment = shipmentMapper.fromRawShipmentDto(rawShipmentDto);
            shipment.setReceiver(personDto.getFirstName().concat(" ").concat(personDto.getLastName()).concat(" ").concat(personDto.getPatronymic()));
            shipmentService.add(shipment);
        } catch (
                FeignException.NotFound e) {
            log.error("person not found");
        }
    }
}
