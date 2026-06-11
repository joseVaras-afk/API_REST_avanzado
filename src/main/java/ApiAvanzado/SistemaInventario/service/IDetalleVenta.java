package ApiAvanzado.SistemaInventario.service;

import ApiAvanzado.SistemaInventario.model.entity.DetalleVenta;
import ApiAvanzado.SistemaInventario.model.entity.dto.DetalleVentaDto;
public interface IDetalleVenta {
    

    DetalleVenta crearDetalleVenta(DetalleVentaDto detalleVentaDto);

    DetalleVenta obtenerDetalleVentaPorId(Integer id);

    Void eliminarDetalleVenta(Integer id);
    
    DetalleVenta actualizarDetalleVenta(Integer id, DetalleVentaDto detalleVentaDto);
}
