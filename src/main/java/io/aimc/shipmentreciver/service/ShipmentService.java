package io.aimc.shipmentreciver.service;


import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.dto.RawShipmentDto;

import java.util.List;
import java.util.UUID;

public interface ShipmentService {
    void add(RawShipmentDto rawShipmentDto);

    List<Shipment> getAllByIds(List<UUID> ids);
}
