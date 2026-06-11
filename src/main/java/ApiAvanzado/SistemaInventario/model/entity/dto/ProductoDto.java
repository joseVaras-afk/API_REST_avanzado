package ApiAvanzado.SistemaInventario.model.entity.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDto implements Serializable {
    
    @NotNull(message = "El id no puede ser nulo")
    private Integer idProducto;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    @NotNull(message = "El precio no puede ser nulo")
    private Double precio;
    @NotNull(message = "El stock no puede ser nulo")
    private Integer stock;
    @NotNull(message = "El id de categoría no puede ser nulo")
    private Integer idCategoria;
}
