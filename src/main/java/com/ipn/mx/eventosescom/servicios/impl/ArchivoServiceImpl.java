package com.ipn.mx.eventosescom.servicios.impl;

import com.ipn.mx.eventosescom.domain.entidades.Archivo;
import com.ipn.mx.eventosescom.domain.repositorios.ArchivoRepository;
import com.ipn.mx.eventosescom.servicios.ArchivoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
@Slf4j
public class ArchivoServiceImpl implements ArchivoService {

    @Autowired
    private ArchivoRepository dao;

    private final String URL_ARCHIVO = "C:\\Users\\Cassandra\\Pictures";

    @Override
    public String guardar(MultipartFile archivo) throws IOException {
        Archivo a = Archivo
                .builder().nombre(archivo.getOriginalFilename())
                .tipo(archivo.getContentType())
                .datos(archivo.getBytes())
                .build();

        a = dao.save(a);
        if(a.getId() != null) {
            log.info("Archivo guardado con exito");
            return URL_ARCHIVO + "\\" + a.getNombre();
        } else {
            log.error("Error al guardar el archivo");
        }
        return null;
    }

    @Override
    public String guardar2(MultipartFile archivo) throws IOException {
        String ruta = URL_ARCHIVO + "\\" + archivo.getOriginalFilename();
        Archivo a = Archivo
                .builder().nombre(archivo.getOriginalFilename())
                .tipo(archivo.getContentType())
                .url(ruta)
                .datos(archivo.getBytes())
                .build();

        a = dao.save(a);
        archivo.transferTo(new File(ruta));
        if(a.getId() != null) {
            log.info("Archivo guardado con exito 2");
            return URL_ARCHIVO + "\\" + a.getNombre();
        } else {
            log.error("Error al guardar el archivo 2");
        }

        return null;
    }

    @Override
    public List<Archivo> mostrar() {
        return dao.findAll();
    }
}
