package com.ipn.mx.frases7cm1;

import com.ipn.mx.frases7cm1.frase.dominio.entidades.Frase;
import com.ipn.mx.frases7cm1.frase.dominio.repositorios.FraseRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Frases7Cm1Application implements CommandLineRunner {
    @Autowired
    private FraseRepositorio dao;

    @Override
    public void run(String... args) throws Exception {
        /*Frase f = Frase.builder()
                .textoFrase("A que no puedes comer solo una")
                .autorFrase("Sabritas")
                .fechaCreacion( new Date())
                .build();
        System.out.println(dao.save(f));*/
    }

    public static void main(String[] args) {
        SpringApplication.run(Frases7Cm1Application.class, args);
    }

}
