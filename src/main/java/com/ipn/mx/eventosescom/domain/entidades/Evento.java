package com.ipn.mx.eventosescom.domain.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 4, max = 100, message = "El nombre debe tener entre 4 y 100 caracteres")
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "duracion", nullable = false)
    private int duracion;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idEvento")
    @JsonManagedReference
    private List<Asistente> asistentes;

    public void setAsistentes(List<Asistente> asistentes) {
        this.asistentes = asistentes;
        for (Asistente asistente : asistentes) {
            asistente.setIdEvento(this);
        }
    }
}
