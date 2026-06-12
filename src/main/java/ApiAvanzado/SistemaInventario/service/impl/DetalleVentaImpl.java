package ApiAvanzado.SistemaInventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ApiAvanzado.SistemaInventario.service.IDetalleVenta;
import ApiAvanzado.SistemaInventario.repository.DetalleVentaRepo;
import ApiAvanzado.SistemaInventario.model.entity.DetalleVenta;
import ApiAvanzado.SistemaInventario.model.entity.dto.DetalleVentaDto;

public class DetalleVentaImpl implements IDetalleVenta {

    @Autowired
    private DetalleVentaRepo detalleVentaRepo;

    @Override
    @Transactional
    public DetalleVenta crearDetalleVenta(DetalleVentaDto detalleVentaDto) {
        DetalleVenta detalleVenta = DetalleVenta.builder()
                .venta(detalleVentaDto.getIdVenta())
                .idProducto(detalleVentaDto.getIdProducto())
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
            detalleVentaExistente.setIdVenta(detalleVentaDto.getIdVenta());
            detalleVentaExistente.setIdProducto(detalleVentaDto.getIdProducto());
            detalleVentaExistente.setCantidad(detalleVentaDto.getCantidad());
            detalleVentaExistente.setPrecioUnitario(detalleVentaDto.getPrecioUnitario());
            return detalleVentaRepo.save(detalleVentaExistente);
        }
        return null;
    }
    
}
