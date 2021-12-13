package io.aimc.shipmentreciver;

import io.aimc.shipmentreciver.service.PortionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PortionSenderScheduler {
    private final PortionService portionService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Scheduled(fixedDelayString = "${scheduler.fixed-delay}")
    public void send() {
        rabbitTemplate.convertAndSend(queue, portionService.get());
        log.info("send to rabbit");
    }
}
