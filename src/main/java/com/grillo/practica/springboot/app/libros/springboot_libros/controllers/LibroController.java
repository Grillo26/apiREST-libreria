package com.grillo.practica.springboot.app.libros.springboot_libros.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Libro;
import com.grillo.practica.springboot.app.libros.springboot_libros.services.LibroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService service;

    @GetMapping
    public List<Libro> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Libro> libroOptional = service.findById(id);
        if(libroOptional.isPresent()){
            return ResponseEntity.ok(libroOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
        public ResponseEntity<?> create(@Valid @RequestBody Libro libro, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(libro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Libro libro, @PathVariable Long id, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Libro> libroOptional = service.update(id, libro);
        if(libroOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(libroOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Libro libro = new Libro();
        libro.setId(id);
        Optional<Libro> libroOptional = service.delete(id);
        if(libroOptional.isPresent()){
            return ResponseEntity.ok(libroOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> erros = new HashMap<>();
        result.getFieldErrors().forEach(err->{
            erros.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(erros);
    }

}
