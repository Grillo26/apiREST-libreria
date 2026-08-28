package com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.model.ClienteEntity;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ClienteResponseDTO(
        Long id,
        String nombre,
        String email,
        String telefono,
        LocalDateTime fechaRegistro

) {
    public static ClienteResponseDTO fromEntity(ClienteEntity entity){
        if(entity == null) return null;

        return ClienteResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
