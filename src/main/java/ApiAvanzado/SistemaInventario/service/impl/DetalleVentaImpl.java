package ApiAvanzado.SistemaInventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ApiAvanzado.SistemaInventario.service.IDetalleVenta;
import ApiAvanzado.SistemaInventario.repository.DetalleVentaRepo;
import ApiAvanzado.SistemaInventario.repository.VentaRepo;
import ApiAvanzado.SistemaInventario.model.entity.DetalleVenta;
import ApiAvanzado.SistemaInventario.model.entity.Venta;
import ApiAvanzado.SistemaInventario.model.entity.dto.DetalleVentaDto;

public class DetalleVentaImpl implements IDetalleVenta {

    @Autowired
    private DetalleVentaRepo detalleVentaRepo;

    @Autowired
    private VentaRepo ventaRepo;

    @Override
    @Transactional
    public DetalleVenta crearDetalleVenta(DetalleVentaDto detalleVentaDto) {
        Venta venta = ventaRepo.findById(detalleVentaDto.getVenta().getIdVenta()).orElse(null);
        DetalleVenta detalleVenta = DetalleVenta.builder()
                .venta(venta)
                .producto(detalleVentaDto.getProducto())
                .cantidad(detalleVentaDto.getCantidad())
                .precioUnitario(detalleVentaDto.getPrecioUnitario())
                .build();
        return detalleVentaRepo.save(detalleVenta);
    }

    @Transactional(readOnly = true)
    @Override
    public DetalleVenta obtenerDetalleVentaPorId(Integer id) {
        return detalleVentaRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Void eliminarDetalleVenta(Integer id) {
        detalleVentaRepo.deleteById(id);
        return null;
    }

    @Override
    @Transactional
    public DetalleVenta actualizarDetalleVenta(Integer id, DetalleVentaDto detalleVentaDto) {
        DetalleVenta detalleVentaExistente = detalleVentaRepo.findById(id).orElse(null);
        if (detalleVentaExistente != null) {
            detalleVentaExistente.setVenta(detalleVentaDto.getVenta());
            detalleVentaExistente.setProducto(detalleVentaDto.getProducto());
            detalleVentaExistente.setCantidad(detalleVentaDto.getCantidad());
            detalleVentaExistente.setPrecioUnitario(detalleVentaDto.getPrecioUnitario());
            return detalleVentaRepo.save(detalleVentaExistente);
        }
        return null;
    }
    
}
