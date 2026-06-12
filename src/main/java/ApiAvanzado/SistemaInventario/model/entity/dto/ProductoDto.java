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
public class ProductoDto implements Serializable {
    
  
    private Integer idProducto;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    @NotNull(message = "El precio no puede ser nulo")
    private Double precio;
    @NotNull(message = "El stock no puede ser nulo")
    private Integer stock;
    @NotNull(message = "Lacategoria no puede ser nula")
    private CategoriaDto categoria;
}
