package ApiAvanzado.SistemaInventario.service;
import ApiAvanzado.SistemaInventario.model.entity.Usuario;
import ApiAvanzado.SistemaInventario.model.entity.dto.UsuarioDto;


public interface IUsuario {

    Usuario crearUsuario(UsuarioDto usuarioDto);

    UsuarioDto obtenerUsuarioPorId(Integer id);

    Void eliminarUsuario(Integer id);
    
    Usuario actualizarUsuario(Integer id, UsuarioDto usuarioDto);
}
