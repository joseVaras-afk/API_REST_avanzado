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

@Table(name = "productos")
public class Producto {
    

    @Id
    @Column(name = "id_producto")
    private Integer idProducto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "id_categoria")
    private Integer idCategoria;
}
