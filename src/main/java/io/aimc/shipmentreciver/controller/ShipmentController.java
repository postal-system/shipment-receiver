package io.aimc.shipmentreciver.controller;

import io.aimc.shipmentreciver.dto.ShipmentDto;
import io.aimc.shipmentreciver.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/shipments")
public class ShipmentController {
    private final ShipmentService shipmentService;

    @GetMapping()
    public List<ShipmentDto> getAllByIds(@RequestParam("ids") List<UUID> ids) {
        return shipmentService.getAllByIds(ids).stream().map(ShipmentDto::fromShipment).collect(toList());
    }

}
