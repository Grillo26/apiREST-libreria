package com.grillo.practica.springboot.app.libros.springboot_libros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Libro;
import com.grillo.practica.springboot.app.libros.springboot_libros.repositories.LibroRepository;

@Service
public class LibroServicesImpl implements LibroService{

    @Autowired
    private LibroRepository repository;
    
    /*==================== BUSCAR TODO ==================== */
    @Transactional(readOnly = true)
    @Override
    public List<Libro> findAll() {
        return (List<Libro>) repository.findAll();
    }

    /*=================== BUSCAR POR ID ==================== */
    @Transactional(readOnly = true)
    @Override
    public Optional<Libro> findById(Long id) {
        return repository.findById(id);
    }

    /*=================== GUARDAR =========================== */
    @Transactional
    @Override
    public Libro save(Libro libro) {
        return repository.save(libro);
    }

    /*================== ACTUALIZAR ========================== */
    @Transactional
    @Override
    public Optional<Libro> update(Long id, Libro libro) {
        Optional<Libro> libroOptional = repository.findById(id);
        if(libroOptional.isPresent()){
            Libro libroDb = libroOptional.orElseThrow();

            libroDb.setTitulo(libro.getTitulo());
            libroDb.setAutor(libro.getAutor());
            libroDb.setPaginas(libro.getPaginas());

            return Optional.of(repository.save(libroDb)); 
        }
        return libroOptional;
    }

    /*================ ELIMINAR =============================== */
    @Transactional
    @Override
    public Optional<Libro> delete(Long id) {
        Optional<Libro> libroOptional = repository.findById(id);
        libroOptional.ifPresent(libroDb ->{
            repository.delete(libroDb);
        });

        return libroOptional;

    }




}
