package com.grillo.practica.springboot.app.libros.springboot_libros.services;

import java.util.List;
import java.util.Optional;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Empleado;

public interface EmpleadoService {
    List<Empleado> findAll();
    Optional<Empleado> findById(Long id);
    Empleado save(Empleado empleado);
    Optional update(Long id, Empleado empleado);
    Optional <Empleado> delete(Long id);
}
