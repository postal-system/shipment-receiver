package io.aimc.shipmentreciver.service.impl;

import feign.FeignException;
import io.aimc.shipmentreciver.client.ReceiverClient;
import io.aimc.shipmentreciver.dto.ShipmentDto;
import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.model.Person;
import io.aimc.shipmentreciver.model.RawShipment;
import io.aimc.shipmentreciver.repository.ShipmentRepository;
import io.aimc.shipmentreciver.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

    @Override
    public List<ShipmentDto> getAllByIds(List<UUID> ids) {
        return shipmentRepository.findAllById(ids).stream().map(ShipmentDto::fromShipment).collect(toList());
    }
}
