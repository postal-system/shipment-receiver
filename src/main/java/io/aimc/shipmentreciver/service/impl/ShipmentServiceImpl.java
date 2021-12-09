package io.aimc.shipmentreciver.service.impl;

import feign.FeignException;
import io.aimc.shipmentreciver.client.ReceiverClient;
import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.model.Person;
import io.aimc.shipmentreciver.model.RawShipment;
import io.aimc.shipmentreciver.repository.ShipmentRepository;
import io.aimc.shipmentreciver.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ReceiverClient receiverClient;
    private final ShipmentRepository shipmentRepository;

    @Override
    public void add(RawShipment rawShipment) {
        try {
            Person person = receiverClient.getById(rawShipment.getIdReceiver());
            Shipment shipment = Shipment.builder()
                    .sourceId(rawShipment.getSourceId())
                    .rawShipment(rawShipment)
                    .sender(rawShipment.getSender())
                    .content(rawShipment.getContent())
                    .fullName(person.getFirstName().concat(" ").concat(person.getLastName()).concat(" ").concat(person.getPatronymic()))
                    .build();
            shipmentRepository.save(shipment);
        } catch (FeignException.NotFound e) {
            log.error("person not found");
        }
    }
}
