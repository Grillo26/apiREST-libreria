package com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.repository;

import com.grillo.practica.springboot.app.libros.springboot_libros.modules.books.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    boolean existsByIsbn(String isbn);

}
