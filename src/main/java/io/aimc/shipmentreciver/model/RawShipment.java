package io.aimc.shipmentreciver.model;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RawShipment {
    private UUID sourceId;
    private Instant timestamp;
    private Integer idReceiver;
    private String content;
    private String sender;
}
