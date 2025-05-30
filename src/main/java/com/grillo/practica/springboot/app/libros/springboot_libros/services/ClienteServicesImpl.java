package com.grillo.practica.springboot.app.libros.springboot_libros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Cliente;
import com.grillo.practica.springboot.app.libros.springboot_libros.repositories.ClienteRepository;

@Service
public class ClienteServicesImpl implements ClienteService{
    @Autowired
    private ClienteRepository repository;

    /*Buscar Todo */
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) repository.findAll();
    }

    /*Buscar por id */
    @Transactional(readOnly = true)
    @Override
    public Optional<Cliente> findByid(Long id) {
        return repository.findById(id);
    }

    /*Guardar */
    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    /*Actualizar */
    @Transactional
    @Override
    public Optional update(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente clienteDb = clienteOptional.orElseThrow();

            clienteDb.setNombre(cliente.getNombre());
            clienteDb.setEmail(cliente.getEmail());
            clienteDb.setTelefono(cliente.getTelefono());
            clienteDb.setDireccion(cliente.getDireccion());
            clienteDb.setFechaRegistro(cliente.getFechaRegistro());

            return Optional.of(repository.save(clienteDb));
        }
        return clienteOptional;
    }

    /*Eliminar */
    @Override
    public Optional<Cliente> delete(Long id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        clienteOptional.ifPresent(clienteDb-> {
            repository.delete(clienteDb);
        });
        return clienteOptional;
    }
}
