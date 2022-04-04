package io.codero.shipmentreciver.dto;

import lombok.Data;

@Data
public class PostOfficeDto {
    private int id;
    private String name;
    private String address;
    private String operationTime;
    private String metadata;
}
