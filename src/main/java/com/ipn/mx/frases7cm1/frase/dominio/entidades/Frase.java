package com.ipn.mx.frases7cm1.frase.dominio.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Frase")

public class Frase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false) //borrar name(?) investiga porque
    private Long idFrase;

    @Column(nullable = false, length = 100)
    private String autorFrase;

    @Column(nullable = false, length = 500)
    private String textoFrase;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @PrePersist
    public void prePersist() {
        fechaCreacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaCreacion = new Date();
    }

}
