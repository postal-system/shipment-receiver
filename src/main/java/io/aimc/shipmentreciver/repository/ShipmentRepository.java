package io.aimc.shipmentreciver.repository;

import io.aimc.shipmentreciver.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
    @Modifying
    @Query(value = "UPDATE shipment SET portion_id = :portionId WHERE source_id in " +
            "(SELECT source_id FROM shipment WHERE portion_id IS NULL LIMIT :amount)", nativeQuery = true)
    void addToPortion(@Param("amount") int amount, @Param("portionId") UUID portionId);

    @Query(value = "SELECT * FROM shipment WHERE portion_id = :portionId", nativeQuery = true)
    List<Shipment> findAllByPortionId(@Param("portionId") UUID portionId);
}
