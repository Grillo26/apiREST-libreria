package com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.repository;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Boolean existsByEmail(String email);
}
