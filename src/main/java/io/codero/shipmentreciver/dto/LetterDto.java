package io.codero.shipmentreciver.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class LetterDto {
    private UUID id;
    private String sender;
    private String content;
    private Instant timestamp;
    private String receiver;
    private Integer postOfficeId;
    private RawLetterDto rawLetterDto;
}
