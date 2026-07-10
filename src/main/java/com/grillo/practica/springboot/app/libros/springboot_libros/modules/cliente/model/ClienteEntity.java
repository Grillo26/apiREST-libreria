package com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Builder.Default
    @Column(length = 100, unique = true, nullable = false)
    private String email = "no ingresado";

    @Column(length = 20)
    private String telefono;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate(){
        fechaRegistro = LocalDateTime.now();
    }

    @Builder.Default
    @Column(name = "is_eliminado")
    private boolean eliminado = false;

}
