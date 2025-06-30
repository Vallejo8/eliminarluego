package com.ipn.mx.eventosescom.servicios;

import com.ipn.mx.eventosescom.domain.entidades.Evento;

public interface EmailService {
    public void enviarEmail(Evento evento);
    public void enviarNotificacion(String texto);

}
