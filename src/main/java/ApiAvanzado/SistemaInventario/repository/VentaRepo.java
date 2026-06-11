package ApiAvanzado.SistemaInventario.repository;

import org.springframework.data.repository.CrudRepository;
import ApiAvanzado.SistemaInventario.model.entity.Venta;

public interface VentaRepo extends CrudRepository<Venta, Integer> {
    
}
