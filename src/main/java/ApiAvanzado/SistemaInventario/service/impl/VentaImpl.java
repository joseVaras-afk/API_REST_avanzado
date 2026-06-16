package ApiAvanzado.SistemaInventario.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ApiAvanzado.SistemaInventario.service.IVentas;
import ApiAvanzado.SistemaInventario.repository.UsuarioRepo;
import ApiAvanzado.SistemaInventario.repository.VentaRepo;
import ApiAvanzado.SistemaInventario.repository.DetalleVentaRepo;
import ApiAvanzado.SistemaInventario.repository.ProductoRepo;
import ApiAvanzado.SistemaInventario.model.entity.DetalleVenta;
import ApiAvanzado.SistemaInventario.model.entity.Producto;
import ApiAvanzado.SistemaInventario.model.entity.Usuario;
import ApiAvanzado.SistemaInventario.model.entity.dto.UsuarioDto;
import ApiAvanzado.SistemaInventario.model.entity.dto.DetalleVentaDto;
import ApiAvanzado.SistemaInventario.model.entity.Venta;
import ApiAvanzado.SistemaInventario.model.entity.dto.VentaDto;

@Service
public class VentaImpl implements IVentas {

    @Autowired
    private VentaRepo ventaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private DetalleVentaRepo detalleVentaRepo;

    @Autowired
    private ProductoRepo productoRepo;

    /*@Override
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
    }*/

        @Override
    @Transactional
    public Venta crearVenta(VentaDto ventaDto) {
        Venta venta = new Venta();
        venta.setFecha(ventaDto.getFecha());

        Usuario usuario = usuarioRepo.findById(ventaDto.getId_usuario()).orElseThrow(
                    () -> new RuntimeException("usuario incorrecto"));
        venta.setUsuario(usuario);
        double totalVenta = 0.0;

        for (DetalleVentaDto detalleDto : ventaDto.getDetalles()) {
            Producto producto = productoRepo.findById(detalleDto.getIdproducto()).orElseThrow(
                    () -> new RuntimeException("producto no encontrado" + detalleDto.getIdproducto()));

            if (producto.getStock() < detalleDto.getCantidad()) {
                throw new RuntimeException("Stock insuficiente");
            } else {
                producto.setStock(producto.getStock() - detalleDto.getCantidad());
                productoRepo.save(producto);
            }


            DetalleVenta detalle = DetalleVenta.builder()
                    .venta(venta)
                    .producto(producto)
                    .precioUnitario(producto.getPrecio())
                    .cantidad(detalleDto.getCantidad())
                    .build();
            double subtotal = detalle.getCantidad() * producto.getPrecio();
            detalle.setTotal(subtotal);

            totalVenta += subtotal;

            venta.getDetalles().add(detalle);

        }
        venta.setTotal(totalVenta);

        return ventaRepo.save(venta);
    }
    

    @Transactional(readOnly = true)
    @Override
    public VentaDto obtenerVentaPorId(Integer id) {
        Venta venta = ventaRepo.findById(id).orElseThrow(() -> new RuntimeException("venta no encontrada"));

        List<DetalleVentaDto> detallesDto = venta.getDetalles().stream()
                .map(d -> DetalleVentaDto.builder()
                        .idDetalleVenta(d.getIdDetalle())
                        .idproducto(d.getProducto().getIdProducto())
                        .cantidad(d.getCantidad())
                        .precioUnitario(d.getPrecioUnitario())
                        .build())
                .toList();

        return VentaDto.builder()
                .fecha(venta.getFecha())
                .idVenta(venta.getIdVenta())
                .id_usuario(venta.getUsuario().getIdUsuario())
                .detalles(detallesDto)
                .total(venta.getTotal())
                .build();
    }

    @Override
    @Transactional
    public Void eliminarVenta(Integer id) {
         Venta venta = ventaRepo.findById(id).orElseThrow(() -> new RuntimeException("venta no encontrada"));
        List<DetalleVentaDto> detallesDto = venta.getDetalles().stream()
                .map(d -> DetalleVentaDto.builder()
                        .idDetalleVenta(d.getIdDetalle())
                        .idproducto(d.getProducto().getIdProducto())
                        .cantidad(d.getCantidad())
                        // Agrega subtotal/precio unitario si tus DTOs los tienen
                        .build())
                .toList();

        for (DetalleVentaDto detalleVentaDto : detallesDto) {
            detalleVentaRepo.deleteById(detalleVentaDto.getIdDetalleVenta());
        }

        ventaRepo.deleteById(id);
        return null;
    }


    
}
