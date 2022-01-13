package io.aimc.shipmentreciver.facade;

import feign.FeignException;
import io.aimc.shipmentreciver.client.PersonClient;
import io.aimc.shipmentreciver.dto.ParcelDto;
import io.aimc.shipmentreciver.dto.PersonDto;
import io.aimc.shipmentreciver.dto.RawParcelDto;
import io.aimc.shipmentreciver.entity.Parcel;
import io.aimc.shipmentreciver.mapper.ParcelMapper;
import io.aimc.shipmentreciver.service.ParcelService;
import io.aimc.shipmentreciver.util.ShipmentUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParcelFacade {
    private final ParcelMapper parcelMapper;
    private final ParcelService parcelService;
    private final PersonClient personClient;

    public List<ParcelDto> getAllByIds(@RequestParam("ids") List<UUID> ids) {
        return parcelService.getAllByIds(ids).stream().map(parcelMapper::toDto).collect(toList());
    }

    public void add(RawParcelDto rawParcelDto) {
        try {
            PersonDto personDto = personClient.getById(rawParcelDto.getIdReceiver());
            Parcel parcel = parcelMapper.fromRawParcelDto(rawParcelDto);
            String name = ShipmentUtil.getConcatName(personDto);
            parcel.setReceiver(name);
            parcelService.add(parcel);
        } catch (FeignException.NotFound e) {
            log.error("Parcel ID: {} with person with ID: {} not found", rawParcelDto.getSourceId(), rawParcelDto.getIdReceiver());
        }
    }
}
