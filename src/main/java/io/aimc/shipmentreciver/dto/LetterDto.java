package io.aimc.shipmentreciver.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LetterDto {
    private UUID id;
    private String sender;
    private String content;
    private String receiver;
    private String postOfficeName;
    private String postOfficeAddress;
}
