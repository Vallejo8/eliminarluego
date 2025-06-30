package com.ipn.mx.eventosescom.domain.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Asistente", schema = "public")
public class Asistente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long idAsistente;
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column(name = "paterno", length = 50, nullable = false)
    private String paterno;
    @Column(name = "materno", length = 50, nullable = false)
    private String materno;
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaNacimiento", nullable = false)
    private Date fechaNacimiento;
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "idEvento")
    @JsonBackReference
    private Evento idEvento;

}
