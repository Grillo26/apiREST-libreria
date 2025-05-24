package com.grillo.practica.springboot.app.libros.springboot_libros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Empleado;
import com.grillo.practica.springboot.app.libros.springboot_libros.repositories.EmpleadoRepository;


@Service
public class EmpleadoServicesImpl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository repository;

    //<--Listar
    @Transactional(readOnly = true)
    @Override
    public List<Empleado> findAll() {
        return (List<Empleado>) repository.findAll();
    }

    //<--Buscar por Id
    @Transactional(readOnly = true)
    @Override
    public Optional<Empleado> findById(Long id) {
        return repository.findById(id);
    }

    //<--Guardar
    @Transactional
    @Override
    public Empleado save(Empleado empleado) {
        return repository.save(empleado);
    }

    //<--Actualiar
    @Transactional
    @Override
    public Optional update(Long id, Empleado empleado) {
        Optional<Empleado> empleadoOptional = repository.findById(id);
        if(empleadoOptional.isPresent()){
            Empleado empleadoDb = empleadoOptional.orElseThrow();
            empleadoDb.setNombre(empleado.getNombre());
            empleadoDb.setEmail(empleado.getEmail());
            empleadoDb.setTelefono(empleado.getTelefono());
            empleadoDb.setCargo(empleado.getCargo());
            empleadoDb.setFecha_contratacion(empleado.getFecha_contratacion());
            empleadoDb.setActivo(empleado.getActivo());

            return Optional.of(repository.save(empleadoDb));
        }
        return empleadoOptional;
    }

    //<--Eliminar
    @Transactional
    @Override
    public Optional<Empleado> delete(Long id) {
        Optional<Empleado> empleadoOptional = repository.findById(id);
        empleadoOptional.ifPresent(empleadoDb->{
            repository.delete(empleadoDb);
        });
        return empleadoOptional;
    }

}
