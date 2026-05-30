package com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.repository;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

}
