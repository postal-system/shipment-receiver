package io.aimc.shipmentreciver.repository;

import io.aimc.shipmentreciver.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
    @Query(value = "SELECT * FROM shipment WHERE portion_id IS NULL LIMIT :amount", nativeQuery = true)
    List<Shipment> findNotIncludedInPortion(@Param("amount") int amount);
}
