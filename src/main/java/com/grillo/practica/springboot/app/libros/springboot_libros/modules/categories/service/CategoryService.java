package com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.service;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto.CategoryRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto.CategoryResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.model.CategoryEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponseDTO crearCategoria(CategoryRequestDTO categoryRequestDTO){

        // 1.- Comprobar si hay uno con el mismo nombre
        if(categoryRepository.existsByNombre(categoryRequestDTO.nombre())){
            throw new RuntimeException("Ya existe categoría con ese nombre: " + categoryRequestDTO.nombre());
        }

        // 2.- Almacenando
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .nombre(categoryRequestDTO.nombre())
                .descripcion(categoryRequestDTO.descripcion())
                .build();

        CategoryEntity categoriaGuardado = categoryRepository.save(categoryEntity);

        // 3.- Devolviendo la respuesta
        return new CategoryResponseDTO(
                categoriaGuardado.getNombre(),
                categoriaGuardado.getDescripcion());
    }
}
