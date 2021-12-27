package io.aimc.shipmentreciver.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ParcelDto {
    private UUID sourceId;
    private Integer postOfficeReceiverId;
    private String receiver;
    private String sender;
}
