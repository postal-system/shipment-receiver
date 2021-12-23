package io.aimc.shipmentreciver.facade;

import feign.FeignException;
import io.aimc.shipmentreciver.client.PersonClient;
import io.aimc.shipmentreciver.dto.PersonDto;
import io.aimc.shipmentreciver.dto.RawShipmentDto;
import io.aimc.shipmentreciver.dto.ShipmentDto;
import io.aimc.shipmentreciver.entity.Shipment;
import io.aimc.shipmentreciver.mapper.ShipmentMapper;
import io.aimc.shipmentreciver.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Component
@Slf4j
@RequiredArgsConstructor
public class ShipmentFacade {
    private final ShipmentService shipmentService;
    private final ShipmentMapper shipmentMapper;
    private final PersonClient personClient;

    public List<ShipmentDto> getAllByIds(@RequestParam("ids") List<UUID> ids) {
        return shipmentService.getAllByIds(ids).stream().map(shipmentMapper::toDto).collect(toList());
    }

    public void add(RawShipmentDto rawShipmentDto) {
        try {
            PersonDto personDto = personClient.getById(rawShipmentDto.getIdReceiver());
            Shipment shipment = shipmentMapper.fromRawShipmentDto(rawShipmentDto);
            shipment.setReceiver(personDto.getFirstName().concat(" ").concat(personDto.getLastName()).concat(" ").concat(personDto.getPatronymic()));
            shipmentService.add(shipment);
        } catch (
                FeignException.NotFound e) {
            log.error("person not found");
        }
    }
}
