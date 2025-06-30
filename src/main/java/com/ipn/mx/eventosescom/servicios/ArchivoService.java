package com.ipn.mx.eventosescom.servicios;

import com.ipn.mx.eventosescom.domain.entidades.Archivo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArchivoService {

    public String guardar(MultipartFile archivo) throws IOException;

    public String guardar2(MultipartFile archivo) throws IOException;

    public List<Archivo> mostrar();

}
