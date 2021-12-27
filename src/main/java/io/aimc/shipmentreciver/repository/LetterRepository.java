package io.aimc.shipmentreciver.repository;

import io.aimc.shipmentreciver.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LetterRepository extends JpaRepository<Letter, UUID> {
    @Query(value = "SELECT * FROM letter WHERE portion_id IS NULL LIMIT :amount", nativeQuery = true)
    List<Letter> getUnprocessed(@Param("amount") Integer amount);
}
