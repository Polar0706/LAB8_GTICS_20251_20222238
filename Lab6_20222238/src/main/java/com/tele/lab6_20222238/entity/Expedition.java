package com.tele.lab6_20222238.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Expedition {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreMision;

    @ManyToOne(optional = false)
    private Planet planetaDestino;

    @Column(nullable = false)
    private LocalDateTime fechaLanzamiento;

    @Column(nullable = false)
    private String estado;

    @ManyToMany
    @JoinTable(
            name = "expedition_crew",
            joinColumns = @JoinColumn(name = "expedition_id"),
            inverseJoinColumns = @JoinColumn(name = "crew_member_id")
    )
    private List<CrewMember> tripulacion = new ArrayList<>();

    @Lob
    private String objetivos;

    @Lob
    private String resultados;

    // Getters, setters y constructor vacío
}
