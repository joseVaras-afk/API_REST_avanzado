package ApiAvanzado.SistemaInventario.repository;

import org.springframework.data.repository.CrudRepository;
import ApiAvanzado.SistemaInventario.model.entity.Usuario;

public interface UsuarioRepo extends CrudRepository<Usuario, Integer> {
    
}
