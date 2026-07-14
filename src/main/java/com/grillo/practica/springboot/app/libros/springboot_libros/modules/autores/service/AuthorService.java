package com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.service;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.model.AuthorEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public AuthorResponseDTO crearAuthor(AuthorRequestDTO authorRequestDTO){

        AuthorEntity author = AuthorEntity.builder()
                .nombre(authorRequestDTO.getNombre())
                .nacionalidad(authorRequestDTO.getNacionalidad())
                .build();

        AuthorEntity authorGuardado = authorRepository.save(author);

        return AuthorResponseDTO.builder()
                .nombre(authorGuardado.getNombre())
                .nacionalidad(authorGuardado.getNacionalidad())
                .build();
    }
}
