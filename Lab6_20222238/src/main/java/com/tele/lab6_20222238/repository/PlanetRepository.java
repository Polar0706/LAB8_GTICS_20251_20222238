package com.tele.lab6_20222238.repository;

import com.tele.lab6_20222238.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
    Optional<Planet> findByNombre(String nombre);
}
