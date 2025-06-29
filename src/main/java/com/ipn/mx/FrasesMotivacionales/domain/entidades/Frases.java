package com.ipn.mx.FrasesMotivacionales.domain.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "frases")
public class Frases implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFrase", nullable = false)
    private Long idFrase;
    @Column(name = "textoFrase", nullable = false, length = 500)
    private String textoFrase;
    @Column(name = "autorFrase", nullable = false, length = 100)
    private String autorFrase;
}
