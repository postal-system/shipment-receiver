package io.aimc.shipmentreciver.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Column;
import javax.persistence.InheritanceType;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Shipment {
    @Id
    @Column(name = "id", columnDefinition = "pg-uuid")
    private UUID id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;

    @Column(name = "post_office_name", nullable = false)
    private String postOfficeName;

    @Column(name = "post_office_address", nullable = false)
    private String postOfficeAddress;

    @Column(name = "time_stamp")
    private Instant timestamp;
}
