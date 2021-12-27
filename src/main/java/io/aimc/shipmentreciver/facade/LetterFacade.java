package io.aimc.shipmentreciver.facade;

import feign.FeignException;
import io.aimc.shipmentreciver.client.PersonClient;
import io.aimc.shipmentreciver.dto.LetterDto;
import io.aimc.shipmentreciver.dto.PersonDto;
import io.aimc.shipmentreciver.dto.RawLetterDto;
import io.aimc.shipmentreciver.entity.Letter;
import io.aimc.shipmentreciver.mapper.LetterMapper;
import io.aimc.shipmentreciver.service.LetterService;
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
public class LetterFacade {
    private final LetterService letterService;
    private final LetterMapper shipmentMapper;
    private final PersonClient personClient;

    public List<LetterDto> getAllByIds(@RequestParam("ids") List<UUID> ids) {
        return letterService.getAllByIds(ids).stream().map(shipmentMapper::toDto).collect(toList());
    }

    public void add(RawLetterDto rawLetterDto) {
        try {
            PersonDto personDto = personClient.getById(rawLetterDto.getIdReceiver());
            Letter letter = shipmentMapper.fromRawShipmentDto(rawLetterDto);
            letter.setReceiver(personDto.getFirstName().concat(" ").concat(personDto.getLastName()).concat(" ").concat(personDto.getPatronymic()));
            letterService.add(letter);
        } catch (
                FeignException.NotFound e) {
            log.error("person not found");
        }
    }
}
