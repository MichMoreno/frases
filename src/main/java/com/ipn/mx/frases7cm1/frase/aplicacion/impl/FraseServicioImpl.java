package com.ipn.mx.frases7cm1.frase.aplicacion.impl;

import com.ipn.mx.frases7cm1.frase.dominio.entidades.Frase;
import com.ipn.mx.frases7cm1.frase.dominio.repositorios.FraseRepositorio;
import com.ipn.mx.frases7cm1.frase.aplicacion.FraseServicio;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class FraseServicioImpl implements FraseServicio {

    @Autowired
    private FraseRepositorio fraseRepositorio;

    @Override
    public Frase obtenerFraseAleatoria() {
        return null;
    }
    @Override
    @Transactional
    public Frase guardarFrase(Frase frase) {
        return fraseRepositorio.save(frase);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Frase> obtenerFrases() {
        return (List<Frase>) fraseRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Frase obtenerFrasePorId(Long id) {
        return fraseRepositorio.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Frase eliminarFrase(Long id) {
        fraseRepositorio.deleteById(id);
        return null;
    }

    @Override
    public ByteArrayInputStream reporteDeFrasesPDF(List<Frase> listaDeFrases) {
        Document documento = new Document();
        ByteArrayOutputStream salida = new ByteArrayOutputStream();

        try{
            PdfWriter.getInstance(documento, salida);
            documento.open();
            Font tipoDeLetra = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);
            Paragraph parrafo = new Paragraph("Lista de Frases",  tipoDeLetra);
            parrafo.setAlignment(Element.ALIGN_CENTER);
            documento.add(parrafo);
            documento.add(Chunk.NEWLINE);
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
            PdfPTable tabla = new PdfPTable(4);
            Stream.of("ID", "Frase", "Autor", "Fecha de Creacion")
                    .forEach(headertitle ->{
                        PdfPCell encabezadoTabla = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.BLUE);

                        encabezadoTabla.setBackgroundColor(BaseColor.CYAN);
                        encabezadoTabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                        encabezadoTabla.setVerticalAlignment(Element.ALIGN_CENTER);
                        encabezadoTabla.setBorder(2);
                        encabezadoTabla.setPhrase(new Phrase(headertitle, headFont));
                        tabla.addCell(encabezadoTabla);
                    });
            for (Frase frase : listaDeFrases) {

                PdfPCell celdaId = new PdfPCell(new Phrase(String.valueOf(frase.getIdFrase()), textFont));
                celdaId.setPadding(1);
                celdaId.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaId.setHorizontalAlignment(Element.ALIGN_LEFT);
                tabla.addCell(celdaId);

                PdfPCell celdaFrase = new PdfPCell(new Phrase(frase.getTextoFrase(), textFont));
                celdaFrase.setPadding(1);
                celdaFrase.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaFrase.setHorizontalAlignment(Element.ALIGN_LEFT);
                tabla.addCell(celdaFrase);

                PdfPCell celdaAutor = new PdfPCell(new Phrase(String.valueOf(frase.getAutorFrase()), textFont));
                celdaAutor.setPadding(1);
                celdaAutor.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaAutor.setHorizontalAlignment(Element.ALIGN_LEFT);
                tabla.addCell(celdaAutor);

                PdfPCell celdaFecha = new PdfPCell(new Phrase(String.valueOf(frase.getFechaCreacion()), textFont));
                celdaFecha.setPadding(1);
                celdaFecha.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaFecha.setHorizontalAlignment(Element.ALIGN_LEFT);
                tabla.addCell(celdaFecha);
            }
            documento.add(tabla);
            documento.close();
        }catch(DocumentException e){
            e.printStackTrace();
        }
        return new ByteArrayInputStream(salida.toByteArray());
    }

    /*@Override
    @Transactional
    public Frase obtenerFraseAleatoria(){

    }*/
}
