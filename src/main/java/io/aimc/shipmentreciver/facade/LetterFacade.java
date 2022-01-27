package io.aimc.shipmentreciver.facade;

import feign.FeignException;
import io.aimc.shipmentreciver.client.LetterClient;
import io.aimc.shipmentreciver.client.PersonClient;
import io.aimc.shipmentreciver.client.PostOficeClient;
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
    private final PersonClient personClient;
    private final PostOficeClient postOfficeClient;
    private final ShipmentRepository shipmentRepository;

    public void add(RawLetterDto rawLetterDto) {
        try {
            PersonDto personDto = personClient.getById(rawLetterDto.getIdReceiver());
            LetterDto letterDto = letterMapper.fromRawLetterDto(rawLetterDto);

            postOfficeExist(rawLetterDto);

            String name = ShipmentUtil.getConcatName(personDto);
            letterDto.setReceiver(name);
            letterClient.save(letterDto);
            shipmentRepository.save(new Shipment(letterDto.getId()));
            log.info("send to letter-service: {}", letterDto);
        } catch (FeignException.NotFound e) {
            log.error(
                    "Person ID: {} or post office ID: {} with letter with ID: {} not found",
                    rawLetterDto.getIdReceiver(),
                    rawLetterDto.getPostOfficeId(),
                    rawLetterDto.getId(),
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