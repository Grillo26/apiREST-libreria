package com.grillo.practica.springboot.app.libros.springboot_libros.common.exception;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
