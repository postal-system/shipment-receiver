package io.aimc.shipmentreciver.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Portion {
    private UUID id;
    private List<UUID> shipmentIds;
}
