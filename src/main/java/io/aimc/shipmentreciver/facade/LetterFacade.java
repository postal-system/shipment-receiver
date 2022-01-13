package io.aimc.shipmentreciver.facade;

import feign.FeignException;
import io.aimc.shipmentreciver.client.PersonClient;
import io.aimc.shipmentreciver.dto.LetterDto;
import io.aimc.shipmentreciver.dto.PersonDto;
import io.aimc.shipmentreciver.dto.RawLetterDto;
import io.aimc.shipmentreciver.entity.Letter;
import io.aimc.shipmentreciver.mapper.LetterMapper;
import io.aimc.shipmentreciver.service.LetterService;
import io.aimc.shipmentreciver.util.ShipmentUtil;
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
    private final LetterMapper letterMapper;
    private final PersonClient personClient;

    public List<LetterDto> getAllByIds(@RequestParam("ids") List<UUID> ids) {
        return letterService.getAllByIds(ids).stream().map(letterMapper::toDto).collect(toList());
    }

    public void add(RawLetterDto rawLetterDto) {
        try {
            PersonDto personDto = personClient.getById(rawLetterDto.getIdReceiver());
            Letter letter = letterMapper.fromRawLetterDto(rawLetterDto);
            String name = ShipmentUtil.getConcatName(personDto);
            letter.setReceiver(name);
            letterService.add(letter);
        } catch (
                FeignException.NotFound e) {
            log.error("letter ID: {} with person with ID: {} not found",rawLetterDto.getSourceId() , rawLetterDto.getIdReceiver());
        }
    }
}
