package io.codero.shipmentreciver;

import io.codero.shipmentreciver.service.PortionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PortionSenderScheduler {
    private final PortionService portionService;

    @Scheduled(fixedDelayString = "${scheduler.fixed-delay}")
    public void send() {
        portionService.preparePortion();
    }
}
