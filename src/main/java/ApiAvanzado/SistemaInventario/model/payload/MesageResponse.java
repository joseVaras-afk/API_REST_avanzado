package ApiAvanzado.SistemaInventario.model.payload;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class MesageResponse implements Serializable {
    
    private String message;
    private Object object;
    
}
