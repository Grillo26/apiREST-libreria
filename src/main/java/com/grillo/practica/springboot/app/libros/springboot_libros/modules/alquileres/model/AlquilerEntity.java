package com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.model;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.model.BookEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.model.ClienteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alquileres" )
public class AlquilerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
        cliente_id BIGINT NOT NULL REFERENCES clientes(id) ON DELETE CASCADE,
        libro_id BIGINT NOT NULL REFERENCES libros(id) ON DELETE CASCADE,
    */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id", nullable = false)
    private BookEntity libro;

    @Column(name = "fecha_alquiler", nullable = false)
    private LocalDate fechaAlquiler;

    @Column(name = "fecha_devolucion_prevista", nullable = false)
    private LocalDate fechaDevolucionPrevista;

    @Column(name = "fecha_devolucion_real")
    private LocalDate fechaDevolucionReal;

    @Column(name = "estado", length = 20)
    @Builder.Default
    private String estado = "activo";
}
