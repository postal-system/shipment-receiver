package io.aimc.shipmentreciver.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.aimc.shipmentreciver.dto.RawLetterDto;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Letter extends Shipment {
    @Column
    private String content;

    @Column(name = "portion_id", columnDefinition = "pg-uuid")
    private UUID portionId;

    @Type(type = "jsonb")
    @Column(name = "raw_letter", nullable = false, columnDefinition = "jsonb")
    private RawLetterDto rawLetterDto;

}
