package com.grillo.practica.springboot.app.libros.springboot_libros.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="promociones")
public class Promociones {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotBlank.promociones.titulo}")
    @Size(min = 3, max = 30, message = "{Size.promociones.titulo}")
    private String titulo;

    @NotBlank(message = "{NotBlank.promociones.descripcion}")
    @Size(min = 3, max = 50, message = "{Size.promociones.descripcion}")
    private String descripcion;

    @NotBlank(message = "{NotBlank.promociones.codigo}")
    private String codigo;

    @NotNull(message = "{NotNull.promociones.porcentaje}")
    private Double porcentaje;

    @NotNull(message = "{NotNull.promociones.fecha_inicio}")
    private LocalDate fecha_inicio;

    @NotNull(message = "{NotNull.promociones.fecha_fin}")
    private LocalDate fecha_fin;

    @NotNull(message = "{NotNull.promociones.activa}")
    private Boolean activa;

    public Promociones() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

}
