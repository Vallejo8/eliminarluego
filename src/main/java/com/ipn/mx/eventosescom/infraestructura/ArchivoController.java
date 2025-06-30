package com.ipn.mx.eventosescom.infraestructura;

import com.ipn.mx.eventosescom.domain.entidades.Archivo;
import com.ipn.mx.eventosescom.servicios.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/apiArchivo")
public class ArchivoController {

    @Autowired
    private ArchivoService service;

    @PostMapping("/almacenar")
    public ResponseEntity<String> almacenar(@RequestParam("archivo") MultipartFile archivo) throws IOException {
        String ruta = service.guardar(archivo);
        String respuesta = "Archivo almacenado en: " + ruta;
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}
