package io.aimc.shipmentreciver.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.aimc.shipmentreciver.dto.RawLetterDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.UUID;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Shipment {
    @Id
    @Column(name = "id", columnDefinition = "pg-uuid")
    private UUID sourceId;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;
}
