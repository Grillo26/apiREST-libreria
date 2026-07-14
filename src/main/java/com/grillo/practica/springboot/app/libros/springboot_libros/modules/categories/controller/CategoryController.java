package com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.controller;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ApiResponse;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto.CategoryRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto.CategoryResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> crearCategoria(@Valid @RequestBody CategoryRequestDTO request){
        CategoryResponseDTO response = categoryService.crearCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }
}
