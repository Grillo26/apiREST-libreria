package com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.repository;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Boolean existsByNombre(String nombre);


}
