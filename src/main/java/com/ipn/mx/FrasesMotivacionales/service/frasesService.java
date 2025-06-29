package com.ipn.mx.FrasesMotivacionales.service;

import com.ipn.mx.FrasesMotivacionales.domain.entidades.Frases;

import java.util.List;

public interface frasesService {
    public List<Frases> readAll();
    public Frases findById(Long idFrase);
    public Frases save(Frases frase);
    public void delete(Long idFrase);

    public Frases fraseAleatoria(List<Frases> listaFrases);

    Frases fraseAleatoria();
}
