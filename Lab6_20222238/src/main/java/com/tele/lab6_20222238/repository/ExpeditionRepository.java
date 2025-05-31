package com.tele.lab6_20222238.repository;

import com.tele.lab6_20222238.entity.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpeditionRepository extends JpaRepository<Expedition, Long> {
    @Query("SELECT e FROM Expedition e JOIN e.tripulacion cm WHERE cm.id = :crewId AND e.estado IN ('Planificada', 'En Curso')")
    List<Expedition> findActiveExpeditionsByCrewMemberId(@Param("crewId") Long crewId);
}

