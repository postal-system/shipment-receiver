package io.aimc.shipmentreciver.controller;

import io.aimc.shipmentreciver.dto.LetterDto;
import io.aimc.shipmentreciver.facade.LetterFacade;
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
public class LetterController {
    private final LetterFacade letterFacade;

    @GetMapping()
    public List<LetterDto> getAllByIds(@RequestParam("ids") List<UUID> ids) {
        return letterFacade.getAllByIds(ids);
    }

}
