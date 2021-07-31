/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.tienda.productos.controladores;

import egg.tienda.productos.entidades.Fabricante;
import egg.tienda.productos.errores.ErrorServicio;
import egg.tienda.productos.servicios.FabricanteServicio;
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
@RequestMapping("/fabricante")
public class FabricanteController {

    @Autowired
    private FabricanteServicio fabricanteServicio;

    @GetMapping("/listar")
    public String listar(ModelMap modelo) {

        List<Fabricante> fabricantes = fabricanteServicio.listarActivos();
        modelo.put("fabricantes", fabricantes);
        return "fabricante/listar-fabricantes.html";
    }
    
    @GetMapping("/crear")
    public String crear(ModelMap modelo){
          return "fabricante/form-fabricante.html";
    }

    @PostMapping("/crear")
    public String crear(ModelMap modelo, @RequestParam String nombre) {

        try {
            fabricanteServicio.crear(nombre);
            modelo.put("exito", true);
            return "fabricante/form-fabricante.html";
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
            return "fabricante/form-fabricante.html";
        }
    }
    
    @GetMapping("/baja")
    public String baja(ModelMap modelo, @RequestParam(value = "id") String id) {
        
        fabricanteServicio.baja(id);        
         List<Fabricante> fabricantes = fabricanteServicio.listarActivos();
        modelo.put("fabricantes", fabricantes);   
        return "fabricante/listar-fabricantes.html";
        
    }
}
