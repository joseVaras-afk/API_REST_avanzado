package ApiAvanzado.SistemaInventario.model.entity.dto;
import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaDto implements Serializable {
    
    @NotNull(message = "El id no puede ser nulo")
    private Integer idCategoria;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
}
