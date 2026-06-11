package ApiAvanzado.SistemaInventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ApiAvanzado.SistemaInventario.service.IVentas;
import ApiAvanzado.SistemaInventario.repository.VentaRepo;
import ApiAvanzado.SistemaInventario.model.entity.Venta;
import ApiAvanzado.SistemaInventario.model.entity.dto.VentaDto;

@Service
public class VentaImpl implements IVentas {

    @Autowired
    private VentaRepo ventaRepo;

    @Override
    @Transactional
    public Venta crearVenta(VentaDto ventaDto) {
        Venta venta = Venta.builder()
                .fecha(String.valueOf(ventaDto.getFecha()) )
                .total(ventaDto.getTotal())
                .build();
        return ventaRepo.save(venta);
    }

    @Transactional(readOnly = true)
    @Override
    public Venta obtenerVentaPorId(Integer id) {
        return ventaRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Void eliminarVenta(Integer id) {
        ventaRepo.deleteById(id);
        return null;
    }

    @Override
    @Transactional
    public Venta actualizarVenta(Integer id, VentaDto ventaDto) {
        Venta ventaExistente = ventaRepo.findById(id).orElse(null);
        if (ventaExistente != null) {
            ventaExistente.setFecha(String.valueOf(ventaDto.getFecha()));
            ventaExistente.setTotal(ventaDto.getTotal());
            return ventaRepo.save(ventaExistente);
        }
        return null;
    }
    
}
