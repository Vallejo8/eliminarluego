package com.ipn.mx.eventosescom.infraestructura;

import com.ipn.mx.eventosescom.domain.entidades.Asistente;
import com.ipn.mx.eventosescom.servicios.AsistenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/apiAsistentes")
public class AsistenteController {

    @Autowired
    private AsistenteService service;

    @GetMapping("/asistentes")
    @ResponseStatus(HttpStatus.OK)
    public List<Asistente> listaAsistentes() {
        return service.readAll();
    }

    @GetMapping("/asistentes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Asistente read(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping("/asistentes")
    @ResponseStatus(HttpStatus.CREATED)
    public Asistente create(@RequestBody Asistente asistente) {
        return service.save(asistente);
    }

    @PutMapping("/asistentes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Asistente update(@PathVariable Long id, @RequestBody Asistente asistente) {
        Asistente asistenteActual = service.read(id);
        if(asistenteActual == null) {
            return null;
        }
        asistenteActual.setNombre(asistente.getNombre());
        asistenteActual.setPaterno(asistente.getPaterno());
        asistenteActual.setMaterno(asistente.getMaterno());
        asistenteActual.setEmail(asistente.getEmail());

        return service.save(asistenteActual);
    }

    @DeleteMapping("/asistentes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
