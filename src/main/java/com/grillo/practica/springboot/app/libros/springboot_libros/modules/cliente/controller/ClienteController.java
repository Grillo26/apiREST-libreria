package com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.controller;

import com.grillo.practica.springboot.app.libros.springboot_libros.common.dto.ApiResponse;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto.ClienteRequestDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto.ClienteResponseDTO;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.model.ClienteEntity;
import com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    // Listando los datos usando page
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ClienteResponseDTO>>> listarClientes(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<ClienteResponseDTO> listarPaginada = clienteService.listarClientes(pageable);
        return ResponseEntity.ok(ApiResponse.success("Listado de Clientes", listarPaginada));
    }

    // Crear usando ApiResponse
    @PostMapping
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> crearCliente(@Valid @RequestBody ClienteRequestDTO request){
        ClienteResponseDTO response = clienteService.crearCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> editarCliente(@Valid @RequestBody ClienteRequestDTO requestDTO, @PathVariable Long id ){
        ClienteResponseDTO response = clienteService.editarCliente(id, requestDTO);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> borrarCliente(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // Eliminar Logicamente
    @DeleteMapping("/logico/{id}")
    public ResponseEntity<ApiResponse<Void>> borrarLogicamenteCliente(@PathVariable Long id){
        clienteService.eliminarLogicamenteCliente(id);
        return ResponseEntity.ok(ApiResponse.success("Cliente eliminado con éxito", null));
    }

}
