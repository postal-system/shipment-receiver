package io.aimc.shipmentreciver.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Portion implements Serializable {
    private UUID id;
    private List<UUID> shipmentIds;
}
