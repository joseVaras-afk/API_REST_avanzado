package ApiAvanzado.SistemaInventario.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import ApiAvanzado.SistemaInventario.model.entity.Usuario;
import java.util.List;


public interface UsuarioRepo extends CrudRepository<Usuario, Integer> {
Optional<Usuario> findByNombre(String nombre); 
}
