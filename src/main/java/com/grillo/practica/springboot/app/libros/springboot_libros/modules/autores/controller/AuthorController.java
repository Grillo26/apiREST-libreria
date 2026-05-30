package com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.controller;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ApiResponse;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authores")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponseDTO>> crearAuthor(@Valid @RequestBody AuthorRequestDTO request){
        AuthorResponseDTO created = authorService.crearAuthor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(created));
    }
}
