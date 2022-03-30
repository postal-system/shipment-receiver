package io.aimc.shipmentreciver.facade;

import feign.FeignException;
import io.aimc.shipmentreciver.client.ParcelClient;
import io.aimc.shipmentreciver.client.PostOfficeClient;
import io.aimc.shipmentreciver.client.UserClient;
import io.aimc.shipmentreciver.dto.ParcelDto;
import io.aimc.shipmentreciver.dto.PersonDto;
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
    private final UserClient userClient;
    private final PostOfficeClient postOfficeClient;

    public void add(RawParcelDto rawParcelDto) {
        try {
            PersonDto personDto = userClient.getById(rawParcelDto.getIdReceiver());
            ParcelDto parcelDto = parcelMapper.fromRawParcelDto(rawParcelDto);
            postOfficeExist(rawParcelDto.getPostOfficeId());
            String name = ShipmentUtil.getConcatName(personDto);
            parcelDto.setReceiver(name);
            parcelClient.save(parcelDto);
            log.info("send to parcel-service: {}", parcelDto);
        } catch (FeignException.NotFound feignEx) {
            log.error(
                    "Person ID: {} or post office ID: {} with parcel with ID: {} not found",
                    rawParcelDto.getIdReceiver(),
                    rawParcelDto.getPostOfficeId(),
                    rawParcelDto.getId(),
                    feignEx
            );
        } catch (Exception ex) {
            log.error("Unknown exception", ex);
        }
    }

    private void postOfficeExist(Integer postOfficeId) {
        postOfficeClient.getById(postOfficeId);
    }
}
