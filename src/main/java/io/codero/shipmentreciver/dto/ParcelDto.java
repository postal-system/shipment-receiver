package io.codero.shipmentreciver.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class ParcelDto {
    private UUID id;
    private String sender;
    private String receiver;
    private Instant timestamp;
    private Integer postOfficeId;
    private RawParcelDto rawParcelDto;
}
