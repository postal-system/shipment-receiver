package io.aimc.shipmentreciver.service;

import io.aimc.shipmentreciver.dto.RawLetterDto;
import io.aimc.shipmentreciver.facade.LetterFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LetterListenerService {
    private final LetterFacade letterFacade;


    @KafkaListener(topics = "${spring.kafka.shipment-topic}", containerFactory = "kafkaListenerLetterContainerFactory")
    public void getShipment(RawLetterDto rawLetterDto) {
        log.info("received: {}", rawLetterDto.toString());
        letterFacade.add(rawLetterDto);
    }
}
