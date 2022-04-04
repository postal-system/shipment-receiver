package io.codero.shipmentreciver.service;

import io.codero.shipmentreciver.entity.Shipment;

import java.util.List;
import java.util.UUID;

public interface ShipmentService {
    void add(Shipment shipment);

    List<Shipment> getAllByIds(List<UUID> ids);

}
