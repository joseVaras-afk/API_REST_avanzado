package ApiAvanzado.SistemaInventario.service;

import ApiAvanzado.SistemaInventario.model.entity.Venta;
import ApiAvanzado.SistemaInventario.model.entity.dto.VentaDto;
public interface IVentas {
    
    Venta crearVenta(VentaDto ventaDto);

    VentaDto obtenerVentaPorId(Integer id);

    Void eliminarVenta(Integer id);
    

}
