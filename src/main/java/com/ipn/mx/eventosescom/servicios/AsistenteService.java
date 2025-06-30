package com.ipn.mx.eventosescom.servicios;

import com.ipn.mx.eventosescom.domain.entidades.Asistente;

import java.util.List;

public interface AsistenteService {
    public List<Asistente> readAll();
    public Asistente read(Long id);
    public Asistente save(Asistente asistente);
    public void delete(Long id);
}
