package ApiAvanzado.SistemaInventario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ApiAvanzado.SistemaInventario.model.entity.Categoria;


public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNombre(String nombre);
    
}
