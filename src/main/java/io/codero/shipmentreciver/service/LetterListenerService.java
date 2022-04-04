package io.codero.shipmentreciver.service;

import io.codero.shipmentreciver.dto.RawLetterDto;
import io.codero.shipmentreciver.facade.LetterFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
