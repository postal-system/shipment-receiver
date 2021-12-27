package io.aimc.shipmentreciver.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LetterDto {
    private UUID sourceId;
    private String sender;
    private String content;
    private String receiver;
}
