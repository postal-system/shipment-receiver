package io.aimc.shipmentreciver.mapper;

import io.aimc.shipmentreciver.dto.LetterDto;
import io.aimc.shipmentreciver.dto.RawLetterDto;
import io.aimc.shipmentreciver.entity.Letter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public  interface LetterMapper {
    LetterDto toDto(Letter letter);

    @Mappings({
            @Mapping(target = "rawLetterDto", expression = "java(rawLetterDto)"),
            @Mapping(target = "receiver", ignore = true),
            @Mapping(target = "portionId", ignore = true),
            @Mapping(target = "postOfficeName", ignore = true),
            @Mapping(target = "postOfficeAddress", ignore = true)
    })
    Letter fromRawLetterDto(RawLetterDto rawLetterDto);
}
