package com.ipn.mx.eventosescom.infraestructura;

import com.ipn.mx.eventosescom.domain.entidades.Evento;
import com.ipn.mx.eventosescom.servicios.EmailService;
import com.ipn.mx.eventosescom.servicios.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/apiEventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/eventos")
    @ResponseStatus(HttpStatus.OK)
    public List<Evento> getAll(){
        return eventoService.getAll();
    }

    @GetMapping("/eventos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> read(@PathVariable Long id){
        Evento evento;
        Map<String,Object> response = new HashMap<>();
        try{
            evento = eventoService.read(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(evento == null){
            response.put("mensaje", "El evento ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(evento, HttpStatus.OK);
    }

    @PostMapping("/eventos")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento create(@RequestBody Evento evento){
        System.out.println("Evento: " + evento);
        return eventoService.save(evento);
    }

    @PutMapping("/eventos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Evento update(@PathVariable Long id, @RequestBody Evento evento){
        Evento eventoActual = eventoService.read(id);
        if(eventoActual == null){
            return null;
        }
        eventoActual.setNombre(evento.getNombre());
        eventoActual.setFecha(evento.getFecha());
        eventoActual.setDuracion(evento.getDuracion());
        return eventoService.save(eventoActual);
    }

    @DeleteMapping("/eventos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        Evento evento = eventoService.read(id);
        if(evento == null){
            return;
        }
        eventoService.delete(id);
    }

    @GetMapping("/eventos/reportePDF")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InputStreamResource> reportePDF(){
        List<Evento> eventos = eventoService.getAll();
        ByteArrayInputStream stream = eventoService.reportePDF(eventos);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Eventos.pdf");
        return  ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(stream));
    }
}
