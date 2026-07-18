package com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.controller;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ApiResponse;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto.CategoryRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto.CategoryResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.model.CategoryEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryResponseDTO>>> mostrarCategorias(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<CategoryResponseDTO> categorias = categoryService.listaCategorias(pageable);
        return ResponseEntity.ok(ApiResponse.success("Listado de las Categorias", categorias));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> crearCategoria(@Valid @RequestBody CategoryRequestDTO request){
        CategoryResponseDTO response = categoryService.crearCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> actualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO request){
        CategoryResponseDTO response = categoryService.actualizarCategoria(id, request);
        return ResponseEntity.ok(ApiResponse.success("Categoria Actualizada", response));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarCategoria(@PathVariable Long id){
        categoryService.eliminarCategoria(id);
    }


}
