/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.tienda.productos.servicios;

import egg.tienda.productos.entidades.Fabricante;
import egg.tienda.productos.entidades.Producto;
import egg.tienda.productos.errores.ErrorServicio;
import egg.tienda.productos.repositorios.ProductoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Joaco
 */

@Service
public class ProductoServicio {
    
    @Autowired
    private ProductoRepositorio productoRepositorio;
    
    
    /* Crear nuevo producto */
    @Transactional
    public Producto crear(String nombre, Integer precio, String descripcion /*, Fabricante fabricante*/) throws ErrorServicio{
        
        validar(nombre,precio,descripcion);
        
        Producto producto = new Producto();
        
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setActivo(true);
        /*producto.setFabricante(fabricante);*/
        
        return productoRepositorio.save(producto);         
    }
    
    
    /* Listar todos los productos */
    @Transactional
    public List<Producto> listar () {       
        return productoRepositorio.listarActivos();        
    }
    
    /* dar de baja */
    @Transactional
    public void baja(String id){
        Producto producto = productoRepositorio.getOne(id);
        producto.setActivo(false);    
    }
     
    /* Validar datos */
    private void validar (String nombre, Integer precio, String descripcion) throws ErrorServicio {
        
        if(nombre.isEmpty() || nombre.contains("  ")){
            throw new ErrorServicio("El nombre no puede estar vacío ni contener espacios.");
        }
        
        if(descripcion.isEmpty()){
            throw new ErrorServicio("La descripción no puede estar vacía.");
        }
        
        if(precio == null){
            throw new ErrorServicio("El precio no puede estar vacío ni ser cero.");
        }       
    }   
}
