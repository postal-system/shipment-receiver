package io.aimc.shipmentreciver.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ParcelDto {
    private UUID id;
    private String receiver;
    private String sender;
    private String postOfficeName;
    private String postOfficeAddress;
}
