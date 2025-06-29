package com.ipn.mx.FrasesMotivacionales.infrastructure;

import com.ipn.mx.FrasesMotivacionales.domain.entidades.Frases;
import com.ipn.mx.FrasesMotivacionales.service.frasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FrasesController {
    //http://localhost:8085/api
    @Autowired
    private frasesService service;


    @GetMapping("/frases")
    @ResponseStatus(HttpStatus.OK)
    public List<Frases> readAll(){
        return service.readAll();
    }

    @GetMapping("/frases/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Frases read(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/frases")
    @ResponseStatus(HttpStatus.CREATED)
    public Frases save(@RequestBody Frases frase){
        return service.save(frase);
    }

    @PutMapping("/frases")
    @ResponseStatus(HttpStatus.CREATED)
    public Frases update(@RequestBody Frases frase, @PathVariable Long id){
        Frases fraseExistente = service.findById(id);
        fraseExistente.setTextoFrase(frase.getTextoFrase());
        fraseExistente.setAutorFrase(frase.getAutorFrase());
        return service.save(fraseExistente);
    }

    @DeleteMapping("/frases/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/frases/aleatoria")
    @ResponseStatus(HttpStatus.OK)
    public Frases fraseAleatoria() {
        return service.fraseAleatoria();
    }

}
