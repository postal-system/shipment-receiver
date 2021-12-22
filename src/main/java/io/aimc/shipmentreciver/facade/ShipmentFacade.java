package io.aimc.shipmentreciver.facade;

import io.aimc.shipmentreciver.dto.ShipmentDto;
import io.aimc.shipmentreciver.mapper.ShipmentMapper;
import io.aimc.shipmentreciver.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class ShipmentFacade {
    private final ShipmentService shipmentService;
    private final ShipmentMapper shipmentMapper;

    public List<ShipmentDto> getAllByIds(@RequestParam("ids") List<UUID> ids) {
        return shipmentService.getAllByIds(ids).stream().map(shipmentMapper::toDto).collect(toList());
    }
}
