package com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.service;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ConflictException;
import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ResourceNotFoundException;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto.ClienteRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto.ClienteResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.model.ClienteEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    /*  =========== CRUD CREATE ==============*/
    @Transactional
    public ClienteResponseDTO crearCliente(ClienteRequestDTO clienteRequestDTO){

        // Primero comprobamos si no hay otro con el mismo email
        if(clienteRepository.existsByEmail(clienteRequestDTO.getEmail())){
            throw new RuntimeException("Ya existe un cliente con el mismo email: " + clienteRequestDTO.getEmail());
        }

        // Almacenamos el cliente
        ClienteEntity cliente = ClienteEntity.builder()
                .nombre(clienteRequestDTO.getNombre())
                .email(clienteRequestDTO.getEmail())
                .telefono(clienteRequestDTO.getTelefono())
                .build();

        ClienteEntity clienteGuardado = clienteRepository.save(cliente);

        return ClienteResponseDTO.builder()
                .nombre(clienteGuardado.getNombre())
                .email(clienteGuardado.getEmail())
                .build();
    }

    /*  =========== CRUD UPDATE =============*/
    @Transactional
    public ClienteResponseDTO editarCliente(Long id, ClienteRequestDTO requestDTO){

        // 1.- Primero buscamos si no existe el usuario
        ClienteEntity clienteDb = clienteRepository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("Cliente no encontrado"));

        // 2.- Verificamos el email solo si el usuario está intentando cambiarlo
        if(!clienteDb.getEmail().equals(requestDTO.getEmail())){
            if(clienteRepository.existsByEmail(requestDTO.getEmail())){
                throw new ConflictException(("El email ya esta en uso por otro cliente"));
            }
        }

        // 3.- Si llega aquí es porque existe, modificamos
        clienteDb.setNombre(requestDTO.getNombre());
        clienteDb.setEmail(requestDTO.getEmail());
        clienteDb.setTelefono(requestDTO.getTelefono());

        clienteRepository.save(clienteDb);

        // 3.- Devolvemos el DTO
        return ClienteResponseDTO.builder()
                .nombre(clienteDb.getNombre())
                .email(clienteDb.getEmail())
                .build();
    }

    /*  =========== CRUD HARD DELETE =============*/
    @Transactional
    public void eliminarCliente(Long id){
        // 1.- Verificar si existe
        if(!clienteRepository.existsById(id)){
            throw new ResourceNotFoundException("Cliente no encontrado con el id: "+id);
        }
        clienteRepository.deleteById(id);
    }

    /*  =========== CRUD SOFT DELETE =============*/
    public void eliminarLogicamenteCliente(Long id){
        // 1.- Verificamos si existe el cliente
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));

        // 2.- Cambiar de estado (que llegaría a ser el borrado)
        cliente.setEliminado(true);

        // 3.- Guardamos el cambio
        clienteRepository.save(cliente);
    }


}
