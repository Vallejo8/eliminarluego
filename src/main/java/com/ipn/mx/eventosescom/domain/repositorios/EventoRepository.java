package com.ipn.mx.eventosescom.domain.repositorios;

import com.ipn.mx.eventosescom.domain.entidades.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("select e from Evento e where e.nombre like %?1%")
    public List<Evento> findEventoByNombre(String nombre);
}
