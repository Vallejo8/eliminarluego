package com.ipn.mx.FrasesMotivacionales.service.impl;

import com.ipn.mx.FrasesMotivacionales.domain.entidades.Frases;
import com.ipn.mx.FrasesMotivacionales.domain.repositorios.FrasesRepository;
import com.ipn.mx.FrasesMotivacionales.service.frasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FraseServiceImpl implements frasesService {
    @Autowired
    private FrasesRepository dao;


    @Override
    @Transactional(readOnly = true)
    public List<Frases> readAll() {
        return (List<Frases>) dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Frases findById(Long idFrase) {
        return dao.findById(idFrase).orElse(null);
    }

    @Override
    @Transactional
    public Frases save(Frases frase) {
        return dao.save(frase);
    }

    @Override
    @Transactional
    public void delete(Long idFrase) {
        dao.deleteById(idFrase);
    }

    @Override
    public Frases fraseAleatoria(List<Frases> listaFrases) {
        return null;
    }

    @Override
    public Frases fraseAleatoria() {
        //Show a random phrase
        List<Frases> listaFrases = readAll();
        if (listaFrases.isEmpty()) {
            return null; // or throw an exception if you prefer
        }
        int randomIndex = (int) (Math.random() * listaFrases.size());
        return listaFrases.get(randomIndex);
    }
}
