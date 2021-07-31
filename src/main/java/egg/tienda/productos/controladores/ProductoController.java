/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.tienda.productos.controladores;

import egg.tienda.productos.entidades.Producto;
import egg.tienda.productos.errores.ErrorServicio;
import egg.tienda.productos.servicios.ProductoServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Joaco
 */

@Controller
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    private ProductoServicio productoServicio;
    
    @GetMapping("/crear")
    public String crear(ModelMap modelo){
        return "producto/form-producto.html";
    }
    
    /* Si el Integer viene vacío, se rompe */
    @PostMapping("/crear")
    public String guardar (ModelMap modelo, @RequestParam String nombre, @RequestParam String descripcion, @RequestParam Integer precio){    
        
        try {
            productoServicio.crear(nombre, precio, descripcion);
            modelo.put("exito", true);
            return "producto/form-producto";
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("precio", precio);
            modelo.put("descripcion", descripcion);
            return "producto/form-producto";
        }
    }
    
    @GetMapping("/baja")
    public String baja(ModelMap modelo, @RequestParam(value = "id") String id){
        
        productoServicio.baja(id);     
        List<Producto>productos = productoServicio.listar();
        modelo.put("productos", productos);
        return "producto/listar-productos.html";   
    }
    
    @GetMapping("/editar")
    public String editar(ModelMap modelo, @RequestParam(value = "id") String id){   
        Producto producto = productoServicio.buscarPorId(id);      
        modelo.put("producto", producto);
        modelo.put("id", id);
        return "producto/form-producto-editar.html";
    }
    
    @PostMapping("/editar")
    public String modificar(ModelMap modelo, @RequestParam String id, @RequestParam String nombre, @RequestParam Integer precio, @RequestParam String descripcion){
        
        try {
            productoServicio.editar(id, nombre, precio, descripcion);
            modelo.put("exito", true);
            return "producto/form-producto-editar.html";
        } catch (ErrorServicio ex) {
            modelo.put("errror", true);
            return "producto/form-producto-editar.html";
        }        
    }
    
    @GetMapping("/listar")
    public String listar(ModelMap modelo){
        
        List<Producto>productos = productoServicio.listar();
        modelo.put("productos", productos);
        return "producto/listar-productos.html";
    }
    
}
