package com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.service;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto.ClienteRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto.ClienteResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.model.ClienteEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

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
                .id(clienteGuardado.getId())
                .nombre(clienteGuardado.getNombre())
                .email(clienteGuardado.getEmail())
                .telefono(clienteGuardado.getTelefono())
                .fechaRegistro(clienteGuardado.getFechaRegistro())
                .build();
    }

}
