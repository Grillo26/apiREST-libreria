package com.grillo.practica.springboot.app.libros.springboot_libros.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="resenas")
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotBlank.resenas.titulo_libro}")
    private String titulo_libro;

    @NotBlank(message = "{NotBlank.resenas.nombre_usuario}")
    private String nombre_usuario;

    @NotBlank(message = "{NotBlank.resenas.comentario}")
    private String comentario;

    @NotNull(message = "{NotNull.resenas.calificacion}")
    @Min(value = 0, message = "{Min.resenas.calificacion}")
    @Max(value = 20, message = "{Max.resenas.calificacion}")
    private Integer calificacion;

    @NotNull(message = "{NotNull.resenas.fecha_resena}")
    private LocalDate fecha_resena;
    
    public Resena() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo_libro() {
        return titulo_libro;
    }
    public void setTitulo_libro(String titulo_libro) {
        this.titulo_libro = titulo_libro;
    }
    public String getNombre_usuario() {
        return nombre_usuario;
    }
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Integer getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
    public LocalDate getFecha_resena() {
        return fecha_resena;
    }
    public void setFecha_resena(LocalDate fecha_resena) {
        this.fecha_resena = fecha_resena;
    }

    
}
