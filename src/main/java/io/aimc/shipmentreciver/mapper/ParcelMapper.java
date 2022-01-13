package io.aimc.shipmentreciver.mapper;

import io.aimc.shipmentreciver.dto.ParcelDto;
import io.aimc.shipmentreciver.dto.RawLetterDto;
import io.aimc.shipmentreciver.dto.RawParcelDto;
import io.aimc.shipmentreciver.entity.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ParcelMapper {
    ParcelDto toDto(Parcel parcel);

    @Mappings({
            @Mapping(target = "rawParcelDto", expression = "java(rawParcelDto)"),
            @Mapping(target = "receiver", ignore = true),
            @Mapping(target = "postOfficeName", ignore = true),
            @Mapping(target = "postOfficeAddress", ignore = true)
    })
    Parcel fromRawParcelDto(RawParcelDto rawParcelDto);
}
