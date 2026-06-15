package ApiAvanzado.SistemaInventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ApiAvanzado.SistemaInventario.service.IUsuario;
import ApiAvanzado.SistemaInventario.repository.UsuarioRepo;
import ApiAvanzado.SistemaInventario.model.entity.Usuario;
import ApiAvanzado.SistemaInventario.model.entity.dto.UsuarioDto;
import jakarta.validation.Valid;
import ApiAvanzado.SistemaInventario.model.payload.MesageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuario usuarioService;

    @PostMapping("usuario")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        try {
            Usuario nuevoUsuario = usuarioService.crearUsuario(usuarioDto);
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Usuario creado exitosamente")
                    .object(nuevoUsuario)
                    .build(), HttpStatus.CREATED);
            } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Error al acceder a la base de datos de usuario: " + exDt.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("usuario/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@Valid @PathVariable Integer id) {
        try {
            UsuarioDto usuario = usuarioService.obtenerUsuarioPorId(id);
            if (usuario != null) {
                return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Usuario encontrado")
                        .object(usuario)
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Usuario no encontrado")
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Error al acceder a la base de datos: " + exDt.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("usuario/{id}")
    public ResponseEntity<?> eliminarUsuario(@Valid @PathVariable Integer id) {
        UsuarioDto usuario = usuarioService.obtenerUsuarioPorId(id);
        try {
            if (usuario == null) {
                return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Usuario no encontrado")
                        .build(), HttpStatus.NOT_FOUND);
                
            }else {
                usuarioService.eliminarUsuario(id);
                return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Usuario eliminado exitosamente")
                    .build(), HttpStatus.NO_CONTENT);
            }
            
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Error al acceder a la base de datos: " + exDt.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("usuario/{id}")
    public ResponseEntity<?> actualizarUsuario(@Valid @PathVariable Integer id, @RequestBody UsuarioDto usuarioDto) {
        try {
            UsuarioDto usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
            if (usuarioExistente!= null) {
              usuarioService.actualizarUsuario(id, usuarioDto);  
              return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Usuario actualizado exitosamente")
                        .object(usuarioDto)
                        .build(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Usuario no encontrado")
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Error al acceder a la base de datos: " + exDt.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
