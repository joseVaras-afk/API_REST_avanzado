package ApiAvanzado.SistemaInventario.model.entity.dto;
import java.io.Serializable;
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
public class CategoriaDto implements Serializable {
    
    
    private Integer idCategoria;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
}
