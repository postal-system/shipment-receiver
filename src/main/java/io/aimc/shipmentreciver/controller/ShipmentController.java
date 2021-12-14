package io.aimc.shipmentreciver.controller;

import io.aimc.shipmentreciver.dto.ShipmentDto;
import io.aimc.shipmentreciver.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/shipments")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;

    @GetMapping()
    public ResponseEntity<List<ShipmentDto>> getAllByIds(@RequestParam("ids") List<UUID> ids) {
        return ResponseEntity.ok(shipmentService.getAllByIds(ids));
    }

}
