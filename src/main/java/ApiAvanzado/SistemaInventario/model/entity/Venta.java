package ApiAvanzado.SistemaInventario.model.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder

@Table(name = "ventas")
public class Venta {
    @Id
    @Column(name = "id_venta")
    private Integer idVenta;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "total")
    private Double total;

}
