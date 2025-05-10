package com.grillo.practica.springboot.app.libros.springboot_libros.services;

import java.util.List;
import java.util.Optional;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Promociones;

public interface PromocionesService {
    List<Promociones> findAll();
    Optional<Promociones> findById(Long id);
    Promociones save(Promociones promociones);
    Optional update(Long id, Promociones promociones);
    Optional<Promociones> delete(Long id);
}
