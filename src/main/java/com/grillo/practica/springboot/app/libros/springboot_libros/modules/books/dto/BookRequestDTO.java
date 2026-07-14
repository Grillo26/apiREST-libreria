package com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

public record BookRequestDTO(
        @NotBlank(message = "El nombre es requerido")
        @Length(max = 200)
        String titulo,

        @NotNull(message = "El Id del autor es requerido")
        Long autorId,

        @NotNull(message = "El Id de la categoria es obligatoria")
        Long categoriaId,

        @NotBlank(message = "El código ISBN es obligatorio")
        @Length(max = 20)
        String isbn,

        Integer anioPublicacion,

        @PositiveOrZero
        @NotNull(message = "El numero de ejemplares es obligatorio")
        Integer ejemplaresTotal,

        @PositiveOrZero
        @NotNull(message = "El numero de ejemplares disponibles es obligatorio")
        Integer ejemplaresDisponibles

) {
}
