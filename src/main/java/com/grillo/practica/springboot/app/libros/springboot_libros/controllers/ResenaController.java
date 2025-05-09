package com.grillo.practica.springboot.app.libros.springboot_libros.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Resena> create(@RequestBody Resena resena){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(resena));
    }

    /*Actualizar */
    @PutMapping("/{id}")
    public ResponseEntity<Resena> update(@PathVariable Long id, @RequestBody Resena resena){
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

}
