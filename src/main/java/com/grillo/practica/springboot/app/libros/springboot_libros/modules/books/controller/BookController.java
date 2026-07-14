package com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.controller;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ApiResponse;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.dto.BookRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.dto.BookResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.service.BookService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookResponseDTO>> crearLibro(@Valid @RequestBody BookRequestDTO request){
        BookResponseDTO response = bookService.crearBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }
}
