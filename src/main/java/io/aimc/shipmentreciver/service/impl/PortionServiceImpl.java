package io.aimc.shipmentreciver.service.impl;

import io.aimc.shipmentreciver.entity.Letter;
import io.aimc.shipmentreciver.conf.Broker;
import io.aimc.shipmentreciver.model.Portion;
import io.aimc.shipmentreciver.repository.LetterRepository;
import io.aimc.shipmentreciver.service.PortionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class PortionServiceImpl implements PortionService {
    private final LetterRepository letterRepository;
    private final RabbitTemplate rabbitTemplate;
    private final KafkaTemplate<String, Portion> kafkaTemplate;
    @Value("${portion.size}")
    private Integer portionSize;
    @Value("${spring.rabbitmq.portion-queue}")
    private String portionQueue;
    @Value("${spring.kafka.portion-topic}")
    private String portionTopic;
    @Value("${spring.active-broker}")
    private Broker activeBroker;

    @Override
    @Transactional(rollbackOn = {AmqpConnectException.class, SQLException.class})
    public void preparePortion() {
        switch (activeBroker) {
            case KAFKA:
                sendToKafka(create());
                break;
            default:
                sendToRabbitMQ(create());
                break;
        }
    }

    private Portion create() {
        List<Letter> letters = letterRepository.getUnprocessed(portionSize);
        UUID portionId = UUID.randomUUID();
        letters.forEach(shipment -> shipment.setPortionId(portionId));
        letterRepository.saveAll(letters);
        return new Portion(portionId, letters.stream().map(Letter::getId).collect(toList()));
    }

    private void sendToRabbitMQ(Portion portion) {
        rabbitTemplate.convertAndSend(portionQueue, portion);
        log.info("send to rabbit {}", portion);
    }

    private void sendToKafka(Portion portion) {
        kafkaTemplate.send(portionTopic, portion);
        log.info("send to kafka {}", portion);
    }
}
