package ApiAvanzado.SistemaInventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ApiAvanzado.SistemaInventario.service.IVentas;
import ApiAvanzado.SistemaInventario.repository.UsuarioRepo;
import ApiAvanzado.SistemaInventario.repository.VentaRepo;
import ApiAvanzado.SistemaInventario.model.entity.Usuario;
import ApiAvanzado.SistemaInventario.model.entity.dto.UsuarioDto;
import ApiAvanzado.SistemaInventario.model.entity.Venta;
import ApiAvanzado.SistemaInventario.model.entity.dto.VentaDto;

@Service
public class VentaImpl implements IVentas {

    @Autowired
    private VentaRepo ventaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    @Transactional
    public Venta crearVenta(VentaDto ventaDto) {
        Usuario usuarioEncontrado = usuarioRepo.findByNombre(ventaDto.getUsuario().getNombre()).orElseGet(() -> {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(ventaDto.getUsuario().getNombre());
            nuevoUsuario.setContrasena(ventaDto.getUsuario().getContrasena());
            nuevoUsuario.setCorreo(ventaDto.getUsuario().getCorreo());
            nuevoUsuario.setTelefono(ventaDto.getUsuario().getTelefono());
            return nuevoUsuario;
        });
        if (usuarioEncontrado != null) {
            Venta venta = Venta.builder()
                .usuario(usuarioEncontrado)
                .fecha(ventaDto.getFecha() )
                .total(ventaDto.getTotal())
                .build();
            return ventaRepo.save(venta);
        }else{
            return null;
        }
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
