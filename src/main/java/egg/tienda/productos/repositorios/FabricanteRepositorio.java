/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.tienda.productos.repositorios;

import egg.tienda.productos.entidades.Fabricante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joaco
 */

@Repository
public interface FabricanteRepositorio extends JpaRepository<Fabricante, String> {
    
    @Query("SELECT f FROM Fabricante f WHERE f.activo = 1")
    public List<Fabricante> listarActivos();
    
}
