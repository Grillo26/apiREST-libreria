package com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.service;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ConflictException;
import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ResourceNotFoundException;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto.CategoryRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto.CategoryResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.model.CategoryEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Page<CategoryResponseDTO> listaCategorias(Pageable pageable){
        // 1.- Obtenemos de la base de datos
        Page<CategoryEntity> categorias = categoryRepository.findAll(pageable);

        // 2.- Convertimos cada Entity a DTO
        return categorias.map( categoryEntity -> new CategoryResponseDTO(
                categoryEntity.getId(),
                categoryEntity.getNombre(),
                categoryEntity.getDescripcion()
        ));
    }

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
                categoriaGuardado.getId(),
                categoriaGuardado.getNombre(),
                categoriaGuardado.getDescripcion());
    }

    @Transactional
    public CategoryResponseDTO actualizarCategoria(Long id, CategoryRequestDTO request){

        // 1.- Verificamos si está en la base de datos
        CategoryEntity categoryDB = categoryRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Categoría no Encontrada"));

        // 2.- Verificar primero si está cambiando el nombre, luego lo busca en la base de datos
        if(!categoryDB.getNombre().equals(request.nombre())){
            if(categoryRepository.existsByNombre(request.nombre())){
                throw new ConflictException( ("El nombre ya está en uso"));
            }
        }

        // 3.- Si llega hasta aquí es porque no encontró igual en la base de datos
        categoryDB.setNombre(request.nombre());
        categoryDB.setDescripcion(request.descripcion());

        // 4.- Guardar en la base de datos
        categoryRepository.save(categoryDB);

        // 5.- Retornar el response
        return CategoryResponseDTO.builder()
                .id(categoryDB.getId())
                .nombre(categoryDB.getNombre())
                .descripcion(categoryDB.getDescripcion())
                .build();
    }

    @Transactional
    public void eliminarCategoria(Long id){

        // 1.- Primero Verificamos si existe
        if(!categoryRepository.existsById(id)){
            throw new ResourceNotFoundException("Categoria no encontrada con el id: "+id);
        }

        // 2.- Eliminar (Hard delete)
        categoryRepository.deleteById(id);
    }

}
