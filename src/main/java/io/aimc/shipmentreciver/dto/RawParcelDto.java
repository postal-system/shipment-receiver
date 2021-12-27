package io.aimc.shipmentreciver.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RawParcelDto {
    private UUID sourceId;
    private Integer idReceiver;
    private Integer postOfficeReceiverId;
    private String sender;
}
