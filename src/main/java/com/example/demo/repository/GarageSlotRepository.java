package com.example.demo.repository;

import com.example.demo.model.entity.GarageSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GarageSlotRepository extends JpaRepository<GarageSlotEntity, Long> {
    List<GarageSlotEntity> findAllByStatusOrderBySlotNumber(Boolean isEmpty);

    List<GarageSlotEntity> findAllByTicketTicketId(Long ticketId);
}
