package io.aimc.shipmentreciver.service.impl;

import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.model.Portion;
import io.aimc.shipmentreciver.repository.ShipmentRepository;
import io.aimc.shipmentreciver.service.PortionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpConnectException;
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
    @Value("${portion.size}")
    private int portionSize;

    @Override
    @Transactional(rollbackOn = {AmqpConnectException.class, SQLException.class})
    public Portion createPortion() {
        UUID portionId = UUID.randomUUID();
        shipmentRepository.addToPortion(portionSize, portionId);
        List<Shipment> shipments = shipmentRepository.findAllByPortionId(portionId);
        log.info("processed shipment {}", shipments);
        return new Portion(portionId, shipments.stream().map(Shipment::getSourceId).collect(toList()));
    }
}
