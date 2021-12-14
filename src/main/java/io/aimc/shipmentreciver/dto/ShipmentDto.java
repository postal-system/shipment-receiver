package io.aimc.shipmentreciver.dto;

import io.aimc.shipmentreciver.entity.Shipment;
import lombok.Data;
import lombok.val;

import java.util.UUID;

@Data
public class ShipmentDto {
    private UUID sourceId;
    private String sender;
    private String content;
    private String fullName;

    public static ShipmentDto fromShipment(Shipment shipment) {
        val result = new ShipmentDto();
        result.sourceId = shipment.getSourceId();
        result.sender = shipment.getSender();
        result.content = shipment.getContent();
        result.fullName = shipment.getFullName();
        return result;
    }
}
