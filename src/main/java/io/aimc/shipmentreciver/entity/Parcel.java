package io.aimc.shipmentreciver.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.aimc.shipmentreciver.dto.RawLetterDto;
import io.aimc.shipmentreciver.dto.RawParcelDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Parcel extends Shipment {
    @Column(name = "post_office_receiver_id")
    private Integer postOfficeReceiverId;

    @Type(type = "jsonb")
    @Column(name = "raw_parcel", nullable = false, columnDefinition = "jsonb")
    private RawParcelDto rawParcelDto;
}
