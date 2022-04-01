package io.aimc.shipmentreciver.service.impl;

import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.repository.ShipmentRepository;
import io.aimc.shipmentreciver.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository repository;

    @Override
    public void add(Shipment shipment) {
        repository.save(shipment);
    }

    @Override
    public List<Shipment> getAllByIds(List<UUID> ids) {
        return repository.findAllById(ids);
    }
}
