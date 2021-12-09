package io.aimc.shipmentreciver.model;

import lombok.Data;

@Data
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;

}
