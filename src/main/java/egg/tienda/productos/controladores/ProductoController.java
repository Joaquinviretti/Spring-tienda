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
    
    @PostMapping("/baja")
    public String baja(ModelMap modelo){
        
        List<Producto>productos = productoServicio.listar();
        modelo.put("productos", productos);
        return "producto/listar-productos.html";
        
    }
    
    @GetMapping("/editar")
    public String editar(ModelMap modelo){
        return "producto/form-producto-editar.html";
    }
    
    @GetMapping("/listar")
    public String listar(ModelMap modelo){
        
        List<Producto>productos = productoServicio.listar();
        modelo.put("productos", productos);
        return "producto/listar-productos.html";
    }
    
}
