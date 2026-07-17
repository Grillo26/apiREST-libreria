package com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.service;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.model.AuthorEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional( readOnly = true)
    public Page<AuthorResponseDTO> listaAuthor(Pageable pageable){
        // Obtenemos de la base de datos
        Page<AuthorEntity> autores = authorRepository.findAll(pageable);

        //Convertir cada Entity en DTO
        return autores.map( authorEntity -> new AuthorResponseDTO(
                authorEntity.getId(),
                authorEntity.getNombre(),
                authorEntity.getNacionalidad()
        ));
    }

    @Transactional
    public AuthorResponseDTO crearAuthor(AuthorRequestDTO authorRequestDTO){

        AuthorEntity author = AuthorEntity.builder()
                .nombre(authorRequestDTO.getNombre())
                .nacionalidad(authorRequestDTO.getNacionalidad())
                .build();

        AuthorEntity authorGuardado = authorRepository.save(author);

        return AuthorResponseDTO.builder()
                .id(authorGuardado.getId())
                .nombre(authorGuardado.getNombre())
                .nacionalidad(authorGuardado.getNacionalidad())
                .build();
    }


}
