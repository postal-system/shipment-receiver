package io.codero.shipmentreciver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @Column(name = "id", columnDefinition = "pg-uuid")
    private UUID id;
}