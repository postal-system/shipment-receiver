package io.aimc.shipmentreciver.dto;

import lombok.Data;

@Data
public class PersonDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;

}
