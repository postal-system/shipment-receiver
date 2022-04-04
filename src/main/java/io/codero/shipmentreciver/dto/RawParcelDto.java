package io.codero.shipmentreciver.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RawParcelDto {
    private UUID id;
    private Integer postOfficeId;
    private Integer idReceiver;
    private String sender;
    private Instant timestamp;
}
