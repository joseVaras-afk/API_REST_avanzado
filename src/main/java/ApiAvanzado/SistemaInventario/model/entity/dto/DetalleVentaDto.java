package ApiAvanzado.SistemaInventario.model.entity.dto;
import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaDto implements Serializable {
    
    private Integer idDetalleVenta;
    @NotNull(message = "El id de venta no puede ser nulo")
    private Integer idVenta;
    @NotNull(message = "El id de producto no puede ser nulo")
    private Integer idProducto;
    @NotNull(message = "La cantidad no puede ser nula")
    private Integer cantidad;
    @NotNull(message = "El precio no puede ser nulo")
    private Double precioUnitario;

}
