package io.aimc.shipmentreciver.service.impl;

import io.aimc.shipmentreciver.entity.Parcel;
import io.aimc.shipmentreciver.repository.ParcelRepository;
import io.aimc.shipmentreciver.service.ParcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    private final ParcelRepository parcelRepository;

    @Override
    public void add(Parcel parcel) {
        parcelRepository.save(parcel);
    }

    @Override
    public List<Parcel> getAllByIds(List<UUID> ids) {
        return parcelRepository.findAllById(ids);
    }
}
