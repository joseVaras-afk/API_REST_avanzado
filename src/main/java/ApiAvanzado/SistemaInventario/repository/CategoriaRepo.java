package ApiAvanzado.SistemaInventario.repository;

import org.springframework.data.repository.CrudRepository;
import ApiAvanzado.SistemaInventario.model.entity.Categoria;


public interface CategoriaRepo extends CrudRepository<Categoria, Integer> {
    
}
