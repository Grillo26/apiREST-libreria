package com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.controller;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ApiResponse;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authores")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<AuthorResponseDTO>>> listarAuthores(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<AuthorResponseDTO> authoresPaginado = authorService.listaAuthor(pageable);
        return ResponseEntity.ok(ApiResponse.success("Listado de Autores", authoresPaginado));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponseDTO>> crearAuthor(@Valid @RequestBody AuthorRequestDTO request){
        AuthorResponseDTO created = authorService.crearAuthor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(created));
    }

}
