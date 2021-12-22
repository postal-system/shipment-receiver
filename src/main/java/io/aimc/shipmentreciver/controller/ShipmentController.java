package io.aimc.shipmentreciver.controller;

import io.aimc.shipmentreciver.dto.ShipmentDto;
import io.aimc.shipmentreciver.facade.ShipmentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/shipments")
public class ShipmentController {
    private final ShipmentFacade shipmentFacade;

    @GetMapping()
    public List<ShipmentDto> getAllByIds(@RequestParam("ids") List<UUID> ids) {
        return shipmentFacade.getAllByIds(ids);
    }

}
