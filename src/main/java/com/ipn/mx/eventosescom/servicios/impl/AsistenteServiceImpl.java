package com.ipn.mx.eventosescom.servicios.impl;

import com.ipn.mx.eventosescom.domain.entidades.Asistente;
import com.ipn.mx.eventosescom.domain.repositorios.AsistenteRepository;
import com.ipn.mx.eventosescom.servicios.AsistenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AsistenteServiceImpl implements AsistenteService {

    @Autowired
    private AsistenteRepository asistenteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Asistente> readAll() {
        return asistenteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Asistente read(Long id) {
        return asistenteRepository.findById(id).orElse(null);
    }

    @Override
    public Asistente save(Asistente asistente) {
        return asistenteRepository.save(asistente);
    }

    @Override
    public void delete(Long id) {
        asistenteRepository.deleteById(id);
    }
}
