package com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.dto;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.model.BookEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto.CategoryResponseDTO;
import lombok.Builder;

@Builder
public record BookResponseDTO(
        Long id,
        String titulo,
        AuthorResponseDTO autor,
        CategoryResponseDTO categoria,
        String isbn
) {

    // Para convertir limpiamente una entidad a DTO
    public static BookResponseDTO fromEntity(BookEntity entity){
        return BookResponseDTO.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .isbn(entity.getIsbn())
                .autor(AuthorResponseDTO.builder()
                        .id(entity.getAutorId().getId())
                        .nombre(entity.getAutorId().getNombre())
                        .nacionalidad(entity.getAutorId().getNacionalidad())
                        .build())
                .categoria(CategoryResponseDTO.builder()
                        .id(entity.getCategoriaId().getId())
                        .nombre(entity.getCategoriaId().getNombre())
                        .descripcion(entity.getCategoriaId().getDescripcion())
                        .build())
                .isbn(entity.getIsbn())
                .build();
    }
}
