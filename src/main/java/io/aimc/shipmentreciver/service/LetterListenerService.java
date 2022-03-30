package io.aimc.shipmentreciver.service;

import io.aimc.shipmentreciver.dto.RawLetterDto;
import io.aimc.shipmentreciver.facade.LetterFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LetterListenerService {
    private final LetterFacade letterFacade;

    @KafkaListener(topics = "${spring.kafka.letter-topic}", containerFactory = "kafkaListenerLetterContainerFactory")
    public void getLetter(RawLetterDto rawLetterDto) {
        log.info("#### ->: {}", rawLetterDto);
        letterFacade.add(rawLetterDto);
    }
}
