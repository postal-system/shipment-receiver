package io.aimc.shipmentreciver;

import io.aimc.shipmentreciver.service.PortionSenderToRabbitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PortionSenderScheduler {
    private final PortionSenderToRabbitService portionSenderToRabbitService;

    @Scheduled(fixedDelayString = "${scheduler.fixed-delay}")
    public void send() {
        portionSenderToRabbitService.send();
    }
}
