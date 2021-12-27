package io.aimc.shipmentreciver.service;

import io.aimc.shipmentreciver.entity.Parcel;

import java.util.List;
import java.util.UUID;

public interface ParcelService {
    void add(Parcel parcel);

    List<Parcel> getAllByIds(List<UUID> ids);
}
