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

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Cliente;
import com.grillo.practica.springboot.app.libros.springboot_libros.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    /*Listar */
    @GetMapping
    public List<Cliente> list(){
        return service.findAll();
    }

    /*Ver por Id */
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Cliente> clienteOptional = service.findByid(id);
        if(clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /*Crear*/
    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cliente));
    }

    /*Actualizar o editar*/
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id,@RequestBody Cliente cliente){
        Optional<Cliente> clienteOptional = service.update(id, cliente);
        if(clienteOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /*Eliminar */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Cliente> clienteOptional = service.delete(id);
        if(clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }


}
