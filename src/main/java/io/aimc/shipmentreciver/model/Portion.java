package io.aimc.shipmentreciver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Portion {
    private UUID id;
    private List<UUID> shipmentIds;
}
