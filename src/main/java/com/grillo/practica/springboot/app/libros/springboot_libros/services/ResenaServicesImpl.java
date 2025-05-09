package com.grillo.practica.springboot.app.libros.springboot_libros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Resena;
import com.grillo.practica.springboot.app.libros.springboot_libros.repositories.ResenaRepository;

@Service
public class ResenaServicesImpl implements ResenaService {

    @Autowired
    private ResenaRepository repository;

    /*Listar */
    @Transactional(readOnly = true)
    @Override
    public List<Resena> findAll() {
        return (List<Resena>) repository.findAll();
    }

    /*Buscar por Id */
    @Transactional(readOnly = true)
    @Override
    public Optional<Resena> findById(Long id) {
        return repository.findById(id);
    }
    /*Guardar */
    @Transactional
    @Override
    public Resena save(Resena resena) {
        return repository.save(resena);
    }
    
    /*Actualizar */
    @Override
    public Optional update(Long id, Resena resena) {
        Optional<Resena> resenaOptional = repository.findById(id);
        if(resenaOptional.isPresent()){
            Resena resenaDb = resenaOptional.orElseThrow();

            resenaDb.setTitulo_libro(resena.getTitulo_libro());
            resenaDb.setNombre_usuario(resena.getNombre_usuario());
            resenaDb.setComentario(resena.getComentario());
            resenaDb.setCalificacion(resena.getCalificacion());
            resenaDb.setFecha_resena(resena.getFecha_resena());

            return Optional.of(repository.save(resenaDb));
        }
        return resenaOptional;
    }
    
    /*Eliminar */
    @Transactional
    @Override
    public Optional<Resena> delete(Long id) {
        Optional<Resena> resenaOptional = repository.findById(id);
        resenaOptional.ifPresent(resenaDb -> {
            repository.delete(resenaDb);
        });
        return resenaOptional;
    }
    
}
