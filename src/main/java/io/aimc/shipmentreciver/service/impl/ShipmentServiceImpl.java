package io.aimc.shipmentreciver.service.impl;

import io.aimc.shipmentreciver.client.ReceiverClient;
import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.model.Person;
import io.aimc.shipmentreciver.model.RawShipment;
import io.aimc.shipmentreciver.repository.ShipmentRepository;
import io.aimc.shipmentreciver.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ReceiverClient receiverClient;
    private final ShipmentRepository shipmentRepository;

    @Override
    public void add(RawShipment rawShipment) {
        Person person = receiverClient.getById(rawShipment.getIdReceiver());//todo feign exception handler
        Shipment shipment = Shipment.builder()
                .sourceId(rawShipment.getSourceId())
                .rawShipment(rawShipment)
                .sender(rawShipment.getSender())
                .content(rawShipment.getContent())
                .fullName(person.getFirstName().concat(" ").concat(person.getLastName()).concat(" ").concat(person.getPatronymic()))
                .build();
        shipmentRepository.save(shipment);
    }
}
