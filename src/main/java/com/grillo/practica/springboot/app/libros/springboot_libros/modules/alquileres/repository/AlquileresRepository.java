package com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.repository;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.model.AlquilerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlquileresRepository extends JpaRepository<AlquilerEntity, Long> {
}
