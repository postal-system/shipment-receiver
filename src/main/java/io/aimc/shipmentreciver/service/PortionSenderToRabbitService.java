package io.aimc.shipmentreciver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PortionSenderToRabbitService {
    private final PortionService portionService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    public void send() {
        rabbitTemplate.convertAndSend(queue, portionService.createPortion());
        log.info("send to rabbit");
    }
}
