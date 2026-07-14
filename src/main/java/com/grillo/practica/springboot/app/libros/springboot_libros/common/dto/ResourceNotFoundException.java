package com.grillo.practica.springboot.app.libros.springboot_libros.common.dto;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
