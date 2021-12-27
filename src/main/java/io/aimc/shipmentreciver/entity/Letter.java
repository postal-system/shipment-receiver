package io.aimc.shipmentreciver.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.aimc.shipmentreciver.dto.RawLetterDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Letter extends Shipment {
    private String content;

    @Column(name = "portion_id", columnDefinition = "pg-uuid")
    private UUID portionId;

    @Type(type = "jsonb")
    @Column(name = "raw_letter", nullable = false, columnDefinition = "jsonb")
    private RawLetterDto rawLetterDto;

    @Column(name = "time_stamp")
    private Instant timestamp;
}
