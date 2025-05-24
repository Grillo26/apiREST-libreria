package com.grillo.practica.springboot.app.libros.springboot_libros.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotBlank.libros.titulo}")
    private String titulo;

    @NotBlank(message = "{NotBlank.libros.autor}")
    private String autor;

    @NotNull(message = "{NotNull.libros.paginas}")
    private Integer paginas;

    
    public Libro() {
    }

    public Libro(String titulo, String autor, Integer paginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
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
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public Integer getPaginas() {
        return paginas;
    }
    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    

}
