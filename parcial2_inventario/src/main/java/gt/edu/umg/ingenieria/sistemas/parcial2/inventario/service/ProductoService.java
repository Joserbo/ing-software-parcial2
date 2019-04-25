package gt.edu.umg.ingenieria.sistemas.parcial2.inventario.service;

import gt.edu.umg.ingenieria.sistemas.core.parcial2.core.model.ProductoEntity;
import gt.edu.umg.ingenieria.sistemas.parcial2.inventario.dao.ProductoRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    
    public List<ProductoEntity> buscarTodos() {
        List<ProductoEntity> lista = (List<ProductoEntity>) this.productoRepository.findAll();
        Collections.sort(lista, Comparator.comparing(ProductoEntity::getName));
        return lista;
    }
    
    public ProductoEntity registrarProducto(ProductoEntity p1){
        String name = p1.getName();
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        p1.setName(name);
        return this.productoRepository.save(p1);        
    }
    
    public String actualizarStock(Long id, Integer cantidad, String opcion){
        ProductoEntity p1 = this.productoRepository.findById(id).get();
        String mensaje = "operacion invalida";
        if(opcion.equals("incrementar"))
        {
            Integer stock = p1.getStock();
            stock = stock + cantidad;
            p1.setStock(stock);
            this.productoRepository.save(p1);
            mensaje = "Stock actualizado [ "+p1.getName()+" "+stock+"]";
            return mensaje;            
        }
        if(opcion.equals("decrementar"))
        {
            Integer stock = p1.getStock();
            stock = stock - cantidad;
            p1.setStock(stock);
            this.productoRepository.save(p1);
            mensaje = "Stock actualizado [ "+p1.getName()+" "+stock+"]";
            return mensaje;            
        }
        else return mensaje;
    }
    
}
