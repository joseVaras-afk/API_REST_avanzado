package ApiAvanzado.SistemaInventario.repository;

import org.springframework.data.repository.CrudRepository;
import ApiAvanzado.SistemaInventario.model.entity.DetalleVenta;

public interface DetalleVentaRepo extends CrudRepository<DetalleVenta, Integer> {
    
}
