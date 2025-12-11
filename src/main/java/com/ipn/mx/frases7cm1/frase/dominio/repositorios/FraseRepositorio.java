package com.ipn.mx.frases7cm1.frase.dominio.repositorios;

import com.ipn.mx.frases7cm1.frase.dominio.entidades.Frase;
import org.springframework.data.repository.CrudRepository;

//las interfaces con intrerfaces se heredan, por eso se usa extends y no implements
public interface FraseRepositorio extends CrudRepository<Frase, Long> {

}
