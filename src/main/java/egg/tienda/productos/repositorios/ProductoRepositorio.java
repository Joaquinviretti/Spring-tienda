/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.tienda.productos.repositorios;

import egg.tienda.productos.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joaco
 */

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String> {
    
    @Query("SELECT p FROM Producto p WHERE p.activo = 1")
    public List <Producto> listarActivos();
    
    
}
