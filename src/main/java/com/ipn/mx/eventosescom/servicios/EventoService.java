package com.ipn.mx.eventosescom.servicios;

import com.ipn.mx.eventosescom.domain.entidades.Evento;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface EventoService {
    public List<Evento> getAll();
    public Evento read(Long id);
    public Evento save(Evento evento);
    public void delete(Long id);

    public ByteArrayInputStream reportePDF(List<Evento> eventos);
}
