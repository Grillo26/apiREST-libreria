package com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthorRequestDTO {
    @NotBlank(message = "El nombre no debe estar vacío")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "La nacionalidad no debe estar vacía")
    @Size(max = 100)
    private String nacionalidad;
}
