package com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.model;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.model.AuthorEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.model.CategoryEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "libros")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private AuthorEntity autorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoryEntity categoriaId;

    @Column(length = 20, unique = true)
    private String isbn;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    @Builder.Default
    @Column(name = "ejemplares_total", nullable = false)
    private Integer ejemplaresTotal = 1;

    @Builder.Default
    @Column(name = "ejemplares_disponibles", nullable = false)
    private Integer ejemplaresDisponibles = 1;



}
