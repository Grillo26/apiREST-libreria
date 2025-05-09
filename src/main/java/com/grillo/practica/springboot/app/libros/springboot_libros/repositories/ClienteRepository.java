package com.grillo.practica.springboot.app.libros.springboot_libros.repositories;

import org.springframework.data.repository.CrudRepository;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Cliente;

public interface ClienteRepository extends CrudRepository <Cliente, Long>{

}
