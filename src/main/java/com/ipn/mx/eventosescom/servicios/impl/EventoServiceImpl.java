package com.ipn.mx.eventosescom.servicios.impl;

import com.ipn.mx.eventosescom.domain.entidades.Evento;
import com.ipn.mx.eventosescom.domain.repositorios.EventoRepository;
import com.ipn.mx.eventosescom.servicios.EventoService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EventoServiceImpl implements EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Evento> getAll() {
        return eventoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Evento read(Long id) {
        return eventoRepository.findById((long) id).orElse(null);
    }

    @Override
    @Transactional
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        eventoRepository.deleteById(id);
    }

    @Override
    public ByteArrayInputStream reportePDF(List<Evento> eventos) {
        Document document = new Document();
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, salida);
            document.open();
            Font tipoLetra = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);

            Paragraph parrafo = new Paragraph("Reporte de Eventos", tipoLetra);
            parrafo.setAlignment(Element.ALIGN_CENTER);
            document.add(parrafo);
            document.add(Chunk.NEWLINE);

            tipoLetra = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
            PdfPTable table = new PdfPTable(4);
            Stream.of("Clave","Nombre", "Fecha", "Duracion")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
                        header.setVerticalAlignment(Element.ALIGN_CENTER);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBackgroundColor(BaseColor.CYAN);
                        header.setBorderWidth(1);
                        header.setPhrase(new Phrase(headerTitle, headerFont));
                        table.addCell(header);
                    });

            for (Evento evento : eventos) {
                PdfPCell celdaId = new PdfPCell(new Phrase(String.valueOf(evento.getIdEvento()), tipoLetra));
                celdaId.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaId.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaId.setBorderWidth(1);
                table.addCell(celdaId);

                PdfPCell celdaNombre = new PdfPCell(new Phrase(evento.getNombre(), tipoLetra));
                celdaNombre.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaNombre.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaNombre.setBorderWidth(1);
                table.addCell(celdaNombre);

                PdfPCell celdaFecha = new PdfPCell(new Phrase(String.valueOf(evento.getFecha()), tipoLetra));
                celdaFecha.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaFecha.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaFecha.setBorderWidth(1);
                table.addCell(celdaFecha);

                PdfPCell celdaDuracion = new PdfPCell(new Phrase(String.valueOf(evento.getDuracion()), tipoLetra));
                celdaDuracion.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaDuracion.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaDuracion.setBorderWidth(1);
                table.addCell(celdaDuracion);
            }

            document.add(table);
            document.close();
        }catch (DocumentException e){
            System.err.println("Error al crear el documento PDF:" + e.getMessage());
        }

        return new ByteArrayInputStream(salida.toByteArray());
    }
}
