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

@Table(name = "detalle_venta")
public class DetalleVenta {
    
    @Id
    @Column(name = "id_detalle")
    private Integer idDetalle;
    @Column(name = "id_venta")
    private Integer idVenta;
    @Column(name = "id_producto")
    private Integer idProducto;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precio_unitario")
    private Double precioUnitario;
    @Column(name = "total")
    private Double total;
}
