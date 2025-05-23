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

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Promociones;
import com.grillo.practica.springboot.app.libros.springboot_libros.services.PromocionesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/promociones")
public class PromocionesController {

    @Autowired
    private PromocionesService service;

    /*Listar todo<--- */
    @GetMapping
    public List<Promociones> list(){
        return service.findAll();
    }

    /*Buscar por id<--- */
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Promociones> promocionesOptional = service.findById(id);
        if(promocionesOptional.isPresent()){
            return ResponseEntity.ok(promocionesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /*Crear<--- */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Promociones promociones, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(promociones));

    }
 
    /*Actualizar<--- */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Promociones promociones, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<Promociones> promocionesOptional = service.update(id, promociones);
        if(promocionesOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(promocionesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    /*Eliminar<--- */
    @DeleteMapping("/{id}")
    public ResponseEntity<Promociones> delete(@PathVariable Long id){
        Optional<Promociones> promocionesOptional = service.delete(id);
        if(promocionesOptional.isPresent()){
            return ResponseEntity.ok(promocionesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
    
}
