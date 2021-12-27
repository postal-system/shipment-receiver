package io.aimc.shipmentreciver.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RawLetterDto {
    private UUID sourceId;
    private Instant timestamp;
    private Integer idReceiver;
    private String content;
    private String sender;
}
