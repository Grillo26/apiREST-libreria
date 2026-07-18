package com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

public record CategoryRequestDTO(
        @NotBlank(message = "El nombre es requerido")
        @Length(max = 50)
        String nombre,
        String descripcion
) {
}
