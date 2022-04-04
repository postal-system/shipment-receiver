package io.codero.shipmentreciver.repository;

import io.codero.shipmentreciver.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
    @Query(value = "SELECT * FROM shipment LIMIT :amount", nativeQuery = true)
    List<Shipment> getPart(@Param("amount") Integer amount);
}
