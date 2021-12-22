package io.aimc.shipmentreciver.service.impl;

import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.model.Portion;
import io.aimc.shipmentreciver.repository.ShipmentRepository;
import io.aimc.shipmentreciver.service.PortionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
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
    private final ShipmentRepository shipmentRepository;
    private final RabbitTemplate rabbitTemplate;
    @Value("${portion.size}")
    private Integer portionSize;
    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Override
    @Transactional(rollbackOn = {AmqpConnectException.class, SQLException.class})
    public void preparePortion() {
        send(create());
    }

    private Portion create() {
        List<Shipment> shipments = shipmentRepository.getUnprocessed(portionSize);
        UUID portionId = UUID.randomUUID();
        shipments.forEach(shipment -> shipment.setPortionId(portionId));
        shipmentRepository.saveAll(shipments);
        return new Portion(portionId, shipments.stream().map(Shipment::getSourceId).collect(toList()));
    }

    private void send(Portion portion) {
        rabbitTemplate.convertAndSend(queue, portion);
        log.info("send to rabbit");
    }
}
