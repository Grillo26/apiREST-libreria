package com.grillo.practica.springboot.app.libros.springboot_libros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grillo.practica.springboot.app.libros.springboot_libros.entities.Promociones;
import com.grillo.practica.springboot.app.libros.springboot_libros.repositories.PromocionesRepository;

@Service
public class PromocionesServicesImpl implements PromocionesService{

    @Autowired
    private PromocionesRepository repository;

    /*Mostrar todo<---*/
    @Transactional(readOnly = true)
    @Override
    public List<Promociones> findAll() {
        return (List<Promociones>) repository.findAll();
    }
    
    /*Buscar por id<--- */
    @Transactional(readOnly = true)
    @Override
    public Optional<Promociones> findById(Long id) {
        return  repository.findById(id);
    }

    /*Crear<--- */
    @Transactional
    @Override
    public Promociones save(Promociones promociones) {
        return repository.save(promociones);
    }
    
    /*Editar<---*/
    @Transactional
    @Override
    public Optional update(Long id, Promociones promociones) {
        Optional <Promociones> promocionesOptional = repository.findById(id);
        if(promocionesOptional.isPresent()){
            Promociones promocionesDb = promocionesOptional.orElseThrow();

            promocionesDb.setTitulo(promociones.getTitulo());
            promocionesDb.setDescripcion(promociones.getDescripcion());
            promocionesDb.setCodigo(promociones.getCodigo());
            promocionesDb.setPorcentaje(promociones.getPorcentaje());
            promocionesDb.setFecha_inicio(promociones.getFecha_inicio());
            promocionesDb.setFecha_fin(promociones.getFecha_fin());
            promocionesDb.setActiva(promociones.getActiva());

            return Optional.of(repository.save(promocionesDb));
        }
        return promocionesOptional;
    }
    
    /*Eliminar<--- */
    @Transactional
    @Override
    public Optional<Promociones> delete(Long id) {
        Optional <Promociones> promocionesOptional = repository.findById(id);
        promocionesOptional.ifPresent(promocionesDb ->{
            repository.delete(promocionesDb);
        });
        return promocionesOptional;
    }
}
