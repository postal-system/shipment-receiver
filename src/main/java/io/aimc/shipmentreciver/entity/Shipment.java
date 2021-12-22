package io.aimc.shipmentreciver.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.aimc.shipmentreciver.dto.RawShipmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Shipment {
    @Id
    @Column(name = "source_id", columnDefinition = "pg-uuid")
    private UUID sourceId;

    @Type(type = "jsonb")
    @Column(name = "raw_shipment", nullable = false, columnDefinition = "jsonb")
    private RawShipmentDto rawShipmentDto;

    @Column(nullable = false)
    private String sender;

    @Column
    private String content;

    @Column(nullable = false)
    private String receiver;

    @Column(name = "portion_id", columnDefinition = "pg-uuid")
    private UUID portionId;
}
