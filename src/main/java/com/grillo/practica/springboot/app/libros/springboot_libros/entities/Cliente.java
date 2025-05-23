package com.grillo.practica.springboot.app.libros.springboot_libros.entities;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotBlank.clientes.nombre}")
    private String nombre;

    @NotBlank(message = "{NotBlank.clientes.email}")
    private String email;

    @NotNull(message = "{NotNull.clientes.telefono}")
    @Min(value = 10, message = "{Min.clientes.telefono}")
    private Integer telefono;

    @NotBlank(message = "{NotBlank.clientes.direccion}")
    @Size(min = 3, max = 100, message = "{Size.clientes.direccion}")
    private String direccion;
    
    @Column(name= "fecha_registro")
    @NotNull(message = "{NotNull.clientes.fechaRegistro}")
    private LocalDate fechaRegistro;
    
    public Cliente() {
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    

}
