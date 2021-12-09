package io.aimc.shipmentreciver.service.impl;

import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.model.Portion;
import io.aimc.shipmentreciver.repository.ShipmentRepository;
import io.aimc.shipmentreciver.service.PortionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class PortionServiceImpl implements PortionService {
    private final ShipmentRepository shipmentRepository;

    @Override
    public Portion get() {
        List<Shipment> shipments = shipmentRepository.findNotIncludedInPortion(5);//todo изменить на 50 и выпилить отсюда константу
        Portion portion = Portion.builder().id(UUID.randomUUID()).build();
        shipments.forEach(shipment -> shipment.setPortionId(portion.getId()));
        portion.setShipmentIds(shipments.stream().map(Shipment::getSourceId).collect(toList()));
        shipmentRepository.saveAll(shipments);
        return portion;
    }
}
