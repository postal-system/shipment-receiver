package io.aimc.shipmentreciver.facade;

import feign.FeignException;
import io.aimc.shipmentreciver.client.ParcelClient;
import io.aimc.shipmentreciver.client.PersonClient;
import io.aimc.shipmentreciver.client.PostOficeClient;
import io.aimc.shipmentreciver.dto.ParcelDto;
import io.aimc.shipmentreciver.dto.PersonDto;
import io.aimc.shipmentreciver.dto.RawLetterDto;
import io.aimc.shipmentreciver.dto.RawParcelDto;
import io.aimc.shipmentreciver.mapper.ParcelMapper;
import io.aimc.shipmentreciver.util.ShipmentUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParcelFacade {
    private final ParcelMapper parcelMapper;
    private final ParcelClient parcelClient;
    private final PersonClient personClient;
    private final PostOficeClient postOfficeClient;

    public void add(RawParcelDto rawParcelDto) {
        try {
            PersonDto personDto = personClient.getById(rawParcelDto.getIdReceiver());
            ParcelDto parcelDto = parcelMapper.fromRawParcelDto(rawParcelDto);
            String name = ShipmentUtil.getConcatName(personDto);
            parcelDto.setReceiver(name);
            parcelClient.save(parcelDto);
            log.info("send to parcel-service: {}", parcelDto);
        } catch (FeignException.NotFound e) {
            log.error(
                    "Person ID: {} or post office ID: {} with parcel with ID: {} not found",
                    rawParcelDto.getIdReceiver(),
                    rawParcelDto.getPostOfficeId(),
                    rawParcelDto.getId(),
                    e
            );
        } catch (Exception e) {
            log.error("error", e);
        }
    }

    private void postOfficeExist(RawLetterDto rawLetterDto) {
        postOfficeClient.getById(rawLetterDto.getPostOfficeId());
    }
}
