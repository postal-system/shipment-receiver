package io.aimc.shipmentreciver.mapper;

import io.aimc.shipmentreciver.dto.RawShipmentDto;
import io.aimc.shipmentreciver.dto.ShipmentDto;
import io.aimc.shipmentreciver.entity.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public  interface ShipmentMapper {
    ShipmentDto toDto(Shipment shipment);

    @Mappings({
            @Mapping(target = "rawShipmentDto", expression = "java(rawShipmentDto)"),
            @Mapping(target = "receiver", ignore = true),
            @Mapping(target = "portionId", ignore = true)
    })
    Shipment fromRawShipmentDto(RawShipmentDto rawShipmentDto);
}
