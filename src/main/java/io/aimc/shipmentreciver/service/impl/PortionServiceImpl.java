package io.aimc.shipmentreciver.service.impl;

import io.aimc.shipmentreciver.conf.Broker;
import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.repository.ShipmentRepository;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PortionServiceImpl implements PortionService {
    private final ShipmentRepository repository;
    private final RabbitTemplate rabbitTemplate;
    private final KafkaTemplate<String, List<UUID>> kafkaTemplate;
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
        List<UUID> list = repository.getPart(portionSize)
                .stream()
                .map(Shipment::getId)
                .collect(Collectors.toList());
        if (activeBroker == Broker.KAFKA) {
            kafkaTemplate.send(portionTopic, list);
            log.info("send to kafka {}", list);
        } else {
            rabbitTemplate.convertAndSend(portionQueue, list);
            log.info("send to rabbit {}", list);
        }
        list.forEach(repository::deleteById);
    }
}
