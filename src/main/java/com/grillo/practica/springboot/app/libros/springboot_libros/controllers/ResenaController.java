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

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Resena;
import com.grillo.practica.springboot.app.libros.springboot_libros.services.ResenaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private ResenaService service;

    /*Mostrar Todo */
    @GetMapping
    public List<Resena> list(){
        return service.findAll();
    }

    /*Mostrar por id */
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Resena> resenaOptional = service.findById(id);
        if(resenaOptional.isPresent()){
            return ResponseEntity.ok(resenaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /*Crear */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Resena resena, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(resena));
    }

    /*Actualizar */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Resena resena, @PathVariable Long id, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Resena> resenaOptional = service.update(id, resena);
        if(resenaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(resenaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    
    /*Eliminar */
    @DeleteMapping("/{id}")
    public ResponseEntity<Resena> delete(@PathVariable Long id){
        Optional<Resena> resenaOptional = service.delete(id);
        if(resenaOptional.isPresent()){
            return ResponseEntity.ok(resenaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err->{
            errors.put(err.getField(), "El campo "+ err.getField()+ " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);

    }
}
