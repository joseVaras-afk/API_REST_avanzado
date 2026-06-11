package ApiAvanzado.SistemaInventario.model.entity.dto;

import java.io.Serializable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto implements Serializable{
    


    private Integer idUsuario;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    private String correo;
    @NotBlank(message = "El telefono no puede estar vacío")
    private String telefono;
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contrasena;
}
