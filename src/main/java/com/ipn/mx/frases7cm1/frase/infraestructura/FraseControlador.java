package com.ipn.mx.frases7cm1.frase.infraestructura;


import com.ipn.mx.frases7cm1.frase.dominio.entidades.Frase;
import com.ipn.mx.frases7cm1.frase.dominio.repositorios.FraseRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ipn.mx.frases7cm1.frase.aplicacion.FraseServicio;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("api/frases")
public class FraseControlador {
    @Autowired
    private FraseRepositorio fraseRepositorio;
    @Autowired
    private FraseServicio fraseServicio;

    @GetMapping("/frases/aleaotrio")
    @ResponseStatus(HttpStatus.OK)
    public String obtenerFraseAleatorio() {
        return null;
    }

    @GetMapping("/frases")
    @ResponseStatus(HttpStatus.OK)
    public List<Frase> obtenerFrases() {
        return fraseServicio.obtenerFrases();
    }
    @GetMapping("/frases/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Frase obtenerFrasePorId(@PathVariable Long id) {
        return fraseServicio.obtenerFrasePorId(id);
    }

   @PostMapping ("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Frase guardarFrase(@RequestBody Frase frase) {
        return fraseServicio.guardarFrase(frase);
    }

    @DeleteMapping("/frases/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Frase eliminarPorId(@PathVariable Long id) {
        return fraseServicio.eliminarFrase(id);
    }

    @PutMapping("/frases-actualizar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Frase actualizarFrase(
            @RequestBody Frase frase,
            @PathVariable Long id) {
        Frase f = fraseServicio.obtenerFrasePorId(id);
        f.setTextoFrase(frase.getTextoFrase());
        f.setAutorFrase(frase.getAutorFrase());
        f.setFechaCreacion(frase.getFechaCreacion());

        return fraseServicio.guardarFrase(f);
    }

    @GetMapping("/frase/reporte/pdf")
    public ResponseEntity<InputStreamResource> generarReporteDeFrasesPDF() {
        List <Frase> listaDeFrases = fraseServicio.obtenerFrases();
        ByteArrayInputStream bis = fraseServicio.reporteDeFrasesPDF(listaDeFrases);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporte_de_frases.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)

                .body(new InputStreamResource(bis));
    }


}
