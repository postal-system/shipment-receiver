package io.aimc.shipmentreciver.facade;

import feign.FeignException;
import io.aimc.shipmentreciver.client.LetterClient;
import io.aimc.shipmentreciver.client.UserClient;
import io.aimc.shipmentreciver.client.PostOfficeClient;
import io.aimc.shipmentreciver.dto.LetterDto;
import io.aimc.shipmentreciver.dto.PersonDto;
import io.aimc.shipmentreciver.dto.RawLetterDto;
import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.mapper.LetterMapper;
import io.aimc.shipmentreciver.repository.ShipmentRepository;
import io.aimc.shipmentreciver.util.ShipmentUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LetterFacade {
    private final LetterClient letterClient;
    private final LetterMapper letterMapper;
    private final UserClient userClient;
    private final PostOfficeClient postOfficeClient;
    private final ShipmentRepository shipmentRepository;

    public void add(RawLetterDto rawLetterDto) {
        try {
            PersonDto personDto = userClient.getById(rawLetterDto.getIdReceiver());
            LetterDto letterDto = letterMapper.fromRawLetterDto(rawLetterDto);
            postOfficeExist(rawLetterDto.getPostOfficeId());
            String name = ShipmentUtil.getConcatName(personDto);
            letterDto.setReceiver(name);
//            letterDto.setReceiver("e e e");/* заглушка */
            letterClient.save(letterDto);
            shipmentRepository.save(new Shipment(letterDto.getId()));
            log.info("Send to letter-service: {}", letterDto);
        } catch (FeignException.NotFound feignEx) {
            log.error(
                    "Person ID: {} or post office ID: {} with letter with ID: {} not found",
                    rawLetterDto.getIdReceiver(),
                    rawLetterDto.getPostOfficeId(),
                    rawLetterDto.getId(),
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