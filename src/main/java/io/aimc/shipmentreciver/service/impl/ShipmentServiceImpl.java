package io.aimc.shipmentreciver.service.impl;

import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.repository.ShipmentRepository;
import io.aimc.shipmentreciver.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;

    @Override
    public void add(Shipment shipment) {
        shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> getAllByIds(List<UUID> ids) {
        return shipmentRepository.findAllById(ids);
    }
}
