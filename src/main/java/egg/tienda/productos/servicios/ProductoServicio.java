package egg.tienda.productos.servicios;

import egg.tienda.productos.entidades.Fabricante;
import egg.tienda.productos.entidades.Producto;
import egg.tienda.productos.errores.ErrorServicio;
import egg.tienda.productos.repositorios.ProductoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductoServicio {
    
    @Autowired
    private ProductoRepositorio productoRepositorio;
    
    
    /* Crear nuevo producto */
    @Transactional
    public void crear(String nombre, Integer precio, String descripcion, Fabricante fabricante) throws ErrorServicio{
        
        validar(nombre,precio,descripcion);
        
        Producto producto = new Producto();
        
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setActivo(true);
        producto.setFabricante(fabricante);
        
        productoRepositorio.save(producto);         
    }
    
    /* Buscar por ID */
    @Transactional
    public Producto buscarPorId(String id){
        return productoRepositorio.getOne(id);
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
    
    /* modificar */
    @Transactional
    public void editar(String id, String nombre, Integer precio, String descripcion) throws ErrorServicio{
        
         validar(nombre,precio,descripcion);
        
        Producto producto = productoRepositorio.getById(id);
        
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        
        productoRepositorio.save(producto);
   
    }
     
    /* Validar datos */
    private void validar (String nombre, Integer precio, String descripcion) throws ErrorServicio {
        
        if(nombre.isEmpty() || nombre.contains("  ")){
            throw new ErrorServicio("El nombre no puede estar vac??o ni contener espacios.");
        }
        
        if(descripcion.isEmpty()){
            throw new ErrorServicio("La descripci??n no puede estar vac??a.");
        }
        
        if(precio == null){
            throw new ErrorServicio("El precio no puede estar vac??o ni ser cero.");
        }       
    }   
}
