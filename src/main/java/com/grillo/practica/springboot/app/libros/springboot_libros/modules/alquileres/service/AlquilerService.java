package com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.service;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ResourceNotFoundException;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.dto.AlquilerRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.dto.AlquilerResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.model.AlquilerEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.repository.AlquileresRepository;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.model.BookEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.repository.BookRepository;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.model.ClienteEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AlquilerService {

    private final AlquileresRepository alquileresRepository;
    private final ClienteRepository clienteRepository;
    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Page<AlquilerResponseDTO> listarAlquileres(Pageable pageable){
        // 1.- Buscamos en la base de datos los alquileres
        Page<AlquilerEntity> alquileres = alquileresRepository.findAll(pageable);

        // 2.- Creamos el Entity
        return alquileres.map(AlquilerResponseDTO::fromEntity);
    }

    // Crear un Alquiler o un préstamo
    @Transactional
    public AlquilerResponseDTO crearAlquiler(AlquilerRequestDTO requestDTO){

        // 1.- Verificar la exitencia del cliente (Tabla Clientes)
        ClienteEntity cliente = clienteRepository.findById(requestDTO.clienteId())
                .orElseThrow( () -> new ResourceNotFoundException("Cliente no encontrado"));
        if(cliente.isEliminado()){
            throw new IllegalArgumentException("El cliente seleccionado está inactivo");
        }

        // 2.- El libro debe estar en el catálogo y tener al menos un ejemplar
        BookEntity libro = bookRepository.findById(requestDTO.boolkId())
                .orElseThrow( () -> new ResourceNotFoundException("Libro no encontrado"));
        if(libro.getEjemplaresDisponibles() <=0 ){
            throw new IllegalStateException("El libro" + libro.getTitulo() + "no tiene ejemplares disponibles para el préstamo.");
        }

        // 3.- Crear un Registro en la tabla alquileres con estado activo y decrementar -1 al stock
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles()-1);
        bookRepository.save(libro);

        AlquilerEntity alquiler = AlquilerEntity.builder()
                .cliente(cliente)
                .libro(libro)
                .fechaAlquiler(LocalDate.now())
                .fechaDevolucionPrevista(requestDTO.fechaDevolucionPrevista())
                .estado("activo")
                .build();
        AlquilerEntity alquilerGuardado = alquileresRepository.save(alquiler);

        // 4.- Retornamos el response
        return AlquilerResponseDTO.fromEntity(alquilerGuardado);
    }

}
