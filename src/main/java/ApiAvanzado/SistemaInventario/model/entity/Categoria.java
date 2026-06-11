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

@Table(name = "categorias")
public class Categoria {
    
    @Id
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Column(name = "nombre")
    private String nombre;
}
