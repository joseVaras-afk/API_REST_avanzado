package ApiAvanzado.SistemaInventario.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ApiAvanzado.SistemaInventario.service.IUsuario;
import ApiAvanzado.SistemaInventario.repository.UsuarioRepo;
import ApiAvanzado.SistemaInventario.model.entity.Usuario;
import ApiAvanzado.SistemaInventario.model.entity.dto.UsuarioDto;

@Service
public class UsuarioImpl implements IUsuario {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    @Transactional
    public Usuario crearUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = Usuario.builder()
                .nombre(usuarioDto.getNombre())
                .correo(usuarioDto.getCorreo())
                .telefono(usuarioDto.getTelefono())
                .contrasena(usuarioDto.getContrasena())
                .build();
        return usuarioRepo.save(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioDto obtenerUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        return usuario != null ? UsuarioDto.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .correo(usuario.getCorreo())
                .telefono(usuario.getTelefono())
                .contrasena(usuario.getContrasena())
                .build() : null;
    }

    @Override
    @Transactional
    public Void eliminarUsuario(Integer id) {
        usuarioRepo.deleteById(id);
        return null;
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Integer id, UsuarioDto usuarioDto) {
        Usuario usuarioExistente = usuarioRepo.findById(id).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuarioDto.getNombre());
            usuarioExistente.setCorreo(usuarioDto.getCorreo());
            usuarioExistente.setTelefono(usuarioDto.getTelefono());
            usuarioExistente.setContrasena(usuarioDto.getContrasena());
            return usuarioRepo.save(usuarioExistente);
        }else{
            return null;
        }
        
    }
    
}
