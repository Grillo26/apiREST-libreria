package com.grillo.practica.springboot.app.libros.springboot_libros.services;

import java.util.List;
import java.util.Optional;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Cliente;

public interface ClienteService {

    List<Cliente> findAll();
    Optional<Cliente> findByid(Long id);
    Cliente save(Cliente cliente);
    Optional update(Long id, Cliente cliente);
    Optional<Cliente> delete(Long id);

}
