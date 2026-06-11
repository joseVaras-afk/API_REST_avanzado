package ApiAvanzado.SistemaInventario.model.entity.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VentaDto implements Serializable {
    
    @NotNull(message = "El id no puede ser nulo")
    private Integer idVenta;
    @NotNull(message = "El id de usuario no puede ser nulo")
    private Integer idUsuario;
    @NotNull(message = "El id de producto no puede ser nulo")
    private Integer idProducto;
    @NotNull(message = "La cantidad no puede ser nula")
    private Integer cantidad;
    @NotNull(message = "La fecha no puede ser nula")
    private java.time.LocalDate fecha;
    @NotNull(message = "El total no puede ser nulo")
    private Double total;


}
