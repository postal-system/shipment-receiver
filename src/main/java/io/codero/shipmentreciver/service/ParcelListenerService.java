package io.codero.shipmentreciver.service;

import io.codero.shipmentreciver.dto.RawParcelDto;
import io.codero.shipmentreciver.facade.ParcelFacade;
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
    public void getParcel(RawParcelDto rawParcelDto) {
        log.info("#### ->: {}", rawParcelDto);
        parcelFacade.add(rawParcelDto);
    }
}
