package ApiAvanzado.SistemaInventario.model.entity.dto;

import java.io.Serializable;
import java.util.List;

import ApiAvanzado.SistemaInventario.model.entity.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VentaDto implements Serializable {
    
    
    private Integer idVenta;
    @NotNull(message = "El id de usuario no puede ser nulo")
    private Integer id_usuario;
    @NotNull(message = "La fecha no puede ser nula")
    private String fecha;
    private List<DetalleVentaDto> detalles;
    private Double total;


}
