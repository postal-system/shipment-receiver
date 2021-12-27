package io.aimc.shipmentreciver.service.impl;

import io.aimc.shipmentreciver.entity.Letter;
import io.aimc.shipmentreciver.repository.LetterRepository;
import io.aimc.shipmentreciver.service.LetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LetterServiceImpl implements LetterService {
    private final LetterRepository letterRepository;

    @Override
    public void add(Letter letter) {
        letterRepository.save(letter);
    }

    @Override
    public List<Letter> getAllByIds(List<UUID> ids) {
        return letterRepository.findAllById(ids);
    }
}
