package io.aimc.shipmentreciver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Scheduled(fixedDelay = 50000)
    public void send() throws JsonProcessingException {
        rabbitTemplate.convertAndSend(queue, objectMapper.writeValueAsString(portionService.get()));
        log.info("send to rabbit");
    }
}
