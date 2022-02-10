package io.aimc.shipmentreciver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CreatePortionDto {
    private List<UUID> letterIds;
    private LocalDateTime localDateTime;

}
