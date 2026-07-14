package com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.dto;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorResponseDTO;
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
}
