package ApiAvanzado.SistemaInventario.model.entity;

import ApiAvanzado.SistemaInventario.model.entity.dto.CategoriaDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder

@Table(name = "productos")
public class Producto {
    

    @Id
    @Column(name = "id_producto")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "stock")
    private Integer stock;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}
