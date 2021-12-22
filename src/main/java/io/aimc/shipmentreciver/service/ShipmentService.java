package io.aimc.shipmentreciver.service;


import io.aimc.shipmentreciver.entity.Shipment;

import java.util.List;
import java.util.UUID;

public interface ShipmentService {
    void add(Shipment shipment);

    List<Shipment> getAllByIds(List<UUID> ids);
}
