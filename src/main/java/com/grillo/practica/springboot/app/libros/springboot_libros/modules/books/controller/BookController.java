package com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.controller;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ApiResponse;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.dto.BookRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.dto.BookResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.service.BookService;
import jakarta.transaction.Transactional;
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
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<BookResponseDTO>>> listarLibros(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<BookResponseDTO> response = bookService.listarBook(pageable);
        return ResponseEntity.ok(ApiResponse.success("Listado de Autores", response));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<BookResponseDTO>> crearLibro(@Valid @RequestBody BookRequestDTO request){
        BookResponseDTO response = bookService.crearBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDTO>> actualizarLibro(@PathVariable Long id, @Valid @RequestBody BookRequestDTO request){
        BookResponseDTO response = bookService.updateBook(id, request);
        return ResponseEntity.ok(ApiResponse.success("Libro Actualizado", response));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLibro(@PathVariable Long id){
        bookService.eliminarLibro(id);
    }
}
