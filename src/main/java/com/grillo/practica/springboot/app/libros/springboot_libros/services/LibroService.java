package com.grillo.practica.springboot.app.libros.springboot_libros.services;

import java.util.List;
import java.util.Optional;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Libro;

public interface LibroService {

    List<Libro> findAll();
    Optional<Libro> findById(Long id);
    Libro save(Libro libro);
    Optional update(Long id, Libro libro);
    Optional<Libro> delete(Long id);

}
