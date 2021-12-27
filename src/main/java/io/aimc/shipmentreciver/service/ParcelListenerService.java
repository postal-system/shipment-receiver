package io.aimc.shipmentreciver.service;

import io.aimc.shipmentreciver.dto.RawLetterDto;
import io.aimc.shipmentreciver.dto.RawParcelDto;
import io.aimc.shipmentreciver.facade.ParcelFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParcelListenerService {
    private final ParcelFacade parcelFacade;

    @KafkaListener(topics = "${spring.kafka.parcel-topic}", containerFactory = "kafkaListenerParcelContainerFactory")
    public void getLetter(RawParcelDto rawLetterDto) {
        log.info("received: {}", rawLetterDto);
        parcelFacade.add(rawLetterDto);
    }
}
