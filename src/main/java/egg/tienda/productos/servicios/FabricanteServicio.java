/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.tienda.productos.servicios;

import egg.tienda.productos.repositorios.FabricanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import egg.tienda.productos.entidades.Fabricante;
import egg.tienda.productos.errores.ErrorServicio;

/**
 *
 * @author Joaco
 */
import java.util.List;

@Service
public class FabricanteServicio {

    @Autowired
    private FabricanteRepositorio fabricanteRepositorio;

    /* creación */
    @Transactional
    public Fabricante crear(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("el nombre no puede estar vacío");
        } else {

            Fabricante fabricante = new Fabricante();
            fabricante.setNombre(nombre);
            fabricante.setActivo(true);
            fabricanteRepositorio.save(fabricante);
            return fabricante;
        }
    }

    /* listado */
    @Transactional
    public List<Fabricante> listarActivos() {

        return fabricanteRepositorio.listarActivos();

    }
    
    @Transactional
    public Fabricante buscarPorId(String id){
        
        Fabricante fabricante = fabricanteRepositorio.getById(id);       
        return fabricante;      
    }
    
    /* Baja */
    @Transactional
    public void baja(String id){   
        Fabricante fabricante = fabricanteRepositorio.getById(id);
        fabricante.setActivo(false);       
    }
}
