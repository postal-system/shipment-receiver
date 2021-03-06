package io.codero.shipmentreciver.mapper;

import io.codero.shipmentreciver.dto.ParcelDto;
import io.codero.shipmentreciver.dto.RawParcelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ParcelMapper {

    @Mappings({
            @Mapping(target = "rawParcelDto", expression = "java(rawParcelDto)"),
            @Mapping(target = "receiver", ignore = true),
    })
    ParcelDto fromRawParcelDto(RawParcelDto rawParcelDto);
}
