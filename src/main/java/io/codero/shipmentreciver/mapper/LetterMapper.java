package io.codero.shipmentreciver.mapper;

import io.codero.shipmentreciver.dto.LetterDto;
import io.codero.shipmentreciver.dto.RawLetterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface LetterMapper {

    @Mappings({
            @Mapping(target = "rawLetterDto", expression = "java(rawLetterDto)"),
            @Mapping(target = "receiver", ignore = true),
    })
    LetterDto fromRawLetterDto(RawLetterDto rawLetterDto);
}
