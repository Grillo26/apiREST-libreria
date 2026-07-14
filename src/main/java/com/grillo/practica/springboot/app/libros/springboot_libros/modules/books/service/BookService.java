package com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.service;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.exception.ResourceNotFoundException;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto.AuthorResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.model.AuthorEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.repository.AuthorRepository;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.dto.BookRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.dto.BookResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.model.BookEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.repository.BookRepository;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto.CategoryResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.model.CategoryEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public BookResponseDTO crearBook(BookRequestDTO bookRequestDTO){

        // 1.- Comprobar si existe por ISBN
        if(bookRepository.existsByIsbn(bookRequestDTO.isbn())){
            throw new RuntimeException("Ya existe un libro con este ISBN: "+ bookRequestDTO.isbn());
        }

        // 2.- Buscando el Autor que añadimos
        AuthorEntity autor = authorRepository.findById(bookRequestDTO.autorId())
                .orElseThrow( ()-> new ResourceNotFoundException("Autor no encontrado"));

        // 3.- Buscamos la categoría que añadimos
        CategoryEntity categoria = categoryRepository.findById(bookRequestDTO.categoriaId())
                .orElseThrow(()-> new ResourceNotFoundException("Categoría no encontrada"));

        // 4.- Creamos el Entity con los datos del request
        BookEntity libro = BookEntity.builder()
                .titulo(bookRequestDTO.titulo())
                .autorId(autor)
                .categoriaId(categoria)
                .isbn(bookRequestDTO.isbn())
                .anioPublicacion(bookRequestDTO.anioPublicacion())
                .ejemplaresTotal(bookRequestDTO.ejemplaresTotal())
                .ejemplaresDisponibles(bookRequestDTO.ejemplaresDisponibles())
                .build();

        // 5.- Guardando en la base de datos
        BookEntity savedLibro = bookRepository.save(libro);

        // 6.- Mapeando autor y categoria
        AuthorResponseDTO autorResponse = AuthorResponseDTO.builder()
                .nombre(autor.getNombre())
                .nacionalidad(autor.getNacionalidad())
                .build();
        CategoryResponseDTO categoryResponse = CategoryResponseDTO.builder()
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();

        return BookResponseDTO.builder()
                .id(savedLibro.getId())
                .titulo(savedLibro.getTitulo())
                .autor(autorResponse)
                .categoria(categoryResponse)
                .isbn(savedLibro.getIsbn())
                .build();
    }
}
