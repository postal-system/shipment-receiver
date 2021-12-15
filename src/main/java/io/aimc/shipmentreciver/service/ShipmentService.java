package io.aimc.shipmentreciver.service;


import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.model.RawShipment;

import java.util.List;
import java.util.UUID;

public interface ShipmentService {
    void add(RawShipment rawShipment);

    List<Shipment> getAllByIds(List<UUID> ids);
}
