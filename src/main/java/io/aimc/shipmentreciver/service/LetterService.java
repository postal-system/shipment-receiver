package io.aimc.shipmentreciver.service;


import io.aimc.shipmentreciver.entity.Letter;

import java.util.List;
import java.util.UUID;

public interface LetterService {
    void add(Letter letter);

    List<Letter> getAllByIds(List<UUID> ids);
}
