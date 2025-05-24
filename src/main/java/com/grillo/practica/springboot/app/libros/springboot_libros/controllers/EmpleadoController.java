package com.grillo.practica.springboot.app.libros.springboot_libros.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.naming.Binding;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Empleado;
import com.grillo.practica.springboot.app.libros.springboot_libros.services.EmpleadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService service;

    //<--Lista todo
    @GetMapping
    public List<Empleado> list(){
        return service.findAll();
    }

    //<--Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Empleado> empleadoOptional = service.findById(id);
        if(empleadoOptional.isPresent()){
            return ResponseEntity.ok(empleadoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    //<--Crear
    @PostMapping
    public ResponseEntity<?> create(@Valid Empleado empleado, @PathVariable Long id, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(empleado));
    }

    //<--Update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid Empleado empleado, @PathVariable Long id, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Empleado> empleadoOptional = service.update(id, empleado);
        if(empleadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    //<--Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Empleado> empleadoOptional = service.delete(id);
        if ((empleadoOptional.isPresent())) {
            return ResponseEntity.ok(empleadoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    //<--Validacion y configurar mensaje de error personalizado
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
