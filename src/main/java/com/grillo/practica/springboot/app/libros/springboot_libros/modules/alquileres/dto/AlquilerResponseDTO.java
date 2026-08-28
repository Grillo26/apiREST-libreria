package com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.dto;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.model.AlquilerEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.dto.BookResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto.ClienteResponseDTO;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AlquilerResponseDTO(
        Long id,
        /*
            Jamás se debe colocar las Esntidades directas, ClienteEntity, BookEntity
            Deben sus respectivos DTO, BookResponseDTO - ClienteResponseDTO
         */
        ClienteResponseDTO cliente,
        BookResponseDTO libro,
        LocalDate fechaAlquiler,
        LocalDate fechaDevolucionPrevista,
        LocalDate fechaDevolucionReal,
        String estado

) {

    public static AlquilerResponseDTO fromEntity(AlquilerEntity entity){
        if(entity == null) return null;

        return AlquilerResponseDTO.builder()
                .id(entity.getId())
                .cliente(ClienteResponseDTO.fromEntity(entity.getCliente()))
                .libro(BookResponseDTO.fromEntity(entity.getLibro()))
                .fechaAlquiler(entity.getFechaAlquiler())
                .fechaDevolucionPrevista(entity.getFechaDevolucionPrevista())
                .fechaDevolucionReal(entity.getFechaDevolucionReal())
                .estado(entity.getEstado())
                .build();
    }
}
