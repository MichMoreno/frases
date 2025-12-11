package com.ipn.mx.frases7cm1.frase.aplicacion;

import com.ipn.mx.frases7cm1.frase.dominio.entidades.Frase;
import com.itextpdf.text.pdf.qrcode.ByteArray;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
@Service
public interface FraseServicio {
    public Frase obtenerFraseAleatoria();
    public Frase guardarFrase(Frase frase);
    public List<Frase> obtenerFrases();
    public Frase obtenerFrasePorId(Long id);
    public Frase eliminarFrase(Long id);
    public ByteArrayInputStream reporteDeFrasesPDF (List<Frase> listaDeFrases);
}
