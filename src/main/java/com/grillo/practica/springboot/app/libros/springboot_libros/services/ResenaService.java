package com.grillo.practica.springboot.app.libros.springboot_libros.services;

import java.util.List;
import java.util.Optional;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Resena;

public interface ResenaService {

    List<Resena> findAll();
    Optional<Resena> findById(Long id);
    Resena save(Resena resena);
    Optional update(Long id, Resena resena);
    Optional<Resena> delete (Long id);

}
