package com.tele.lab6_20222238.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String tipoPlaneta;

    @Column(nullable = false)
    private boolean habitable;

    @Column(nullable = false)
    private double gravedadRelativa;

    @Lob
    private String descripcion;

}

