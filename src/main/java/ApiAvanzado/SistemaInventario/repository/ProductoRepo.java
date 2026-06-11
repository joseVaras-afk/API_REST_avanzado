package ApiAvanzado.SistemaInventario.repository;

import org.springframework.data.repository.CrudRepository;
import ApiAvanzado.SistemaInventario.model.entity.Producto;

public interface ProductoRepo extends CrudRepository<Producto, Integer> {
    
}
