package com.tele.lab6_20222238.repository;

import com.tele.lab6_20222238.entity.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
    List<CrewMember> findByEspecialidad(String especialidad);
}

