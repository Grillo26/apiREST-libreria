package com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.service;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ResourceNotFoundException;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.model.AuthorEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.repository.AuthorRepository;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto.ClienteRequestDTO;
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

    @Transactional
    public AuthorResponseDTO editarAutor(Long id, AuthorRequestDTO requestDTO){

        // 1.- Primero buscamos si existe en la base de datos
        AuthorEntity autorDb = authorRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Author no Encontrado"));

        // 2.- Modificar
        autorDb.setNombre(requestDTO.getNombre());
        autorDb.setNacionalidad(requestDTO.getNacionalidad());

        // 3.- Guardar
        authorRepository.save(autorDb);

        // 4.- Devolvemos el DTO
        return AuthorResponseDTO.builder()
                .id(autorDb.getId())
                .nombre(autorDb.getNombre())
                .nacionalidad(autorDb.getNacionalidad())
                .build();
    }

    // Esta vez haremos el hard delete
    @Transactional
    public void eliminarAutor(Long id){
         // 1.- Verificar primero si existe
        if(!authorRepository.existsById(id)){
            throw new ResourceNotFoundException("Autor no encontrado con el id: " +id);
        }
        authorRepository.deleteById(id);
    }


}
