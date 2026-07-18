package com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.service;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ConflictException;
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
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<BookResponseDTO> listarBook(Pageable pageable){
        // 1.- Buscamos en la base de datos los libros
        Page<BookEntity> booksDb = bookRepository.findAll(pageable);

        // 2.- Creamos el entity de los libros
        return booksDb.map(BookResponseDTO::fromEntity);
    }

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

        return BookResponseDTO.fromEntity(savedLibro);
    }

    @Transactional
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO){
        // 1.- Verificamos si existe el dato
        BookEntity book = bookRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Libro no encontrado"));

        // 2.- Verificar por ISBN
        if( !book.getIsbn().equals(bookRequestDTO.isbn())){
            if(bookRepository.existsByIsbn(bookRequestDTO.isbn())){
                throw new IllegalArgumentException("Ya existe un libro con este ISBN: "+bookRequestDTO.isbn());
            }
        }

        // 3.- Buscamos si el autor existe
        AuthorEntity autor = authorRepository.findById(bookRequestDTO.autorId())
                        .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));

        // 4.- Buscamos si la categoria existe
        CategoryEntity categoria = categoryRepository.findById(bookRequestDTO.categoriaId())
                        .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));

        // 5.- Ahora intentamos añadir al nuevo book que encontramos en la base de datos
        book.setTitulo(bookRequestDTO.titulo());
        book.setAutorId(autor);
        book.setCategoriaId(categoria);
        book.setIsbn(bookRequestDTO.isbn());
        book.setAnioPublicacion(bookRequestDTO.anioPublicacion());
        book.setEjemplaresTotal(bookRequestDTO.ejemplaresTotal());
        book.setEjemplaresDisponibles(bookRequestDTO.ejemplaresDisponibles());

        // 6.- Guardamos en la base de datos
        BookEntity bookActualizado = bookRepository.save(book);

        return BookResponseDTO.fromEntity(bookActualizado);
    }

    @Transactional
    public void eliminarLibro(Long id){
        // 1.- Buscamos si existe primero
        if(!bookRepository.existsById(id)){
            throw new ResourceNotFoundException("Libro no encontrado: " + id);
        }
        bookRepository.deleteById(id);
    }

}
