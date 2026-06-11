package ApiAvanzado.SistemaInventario.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ApiAvanzado.SistemaInventario.service.IProducto;
import ApiAvanzado.SistemaInventario.repository.ProductoRepo;
import ApiAvanzado.SistemaInventario.model.entity.Producto;
import ApiAvanzado.SistemaInventario.model.entity.dto.ProductoDto;
import ApiAvanzado.SistemaInventario.model.entity.dto.UsuarioDto;

@Service
public class ProductoImpl implements IProducto {

    @Autowired
    private ProductoRepo productoRepo;

    @Override
    @Transactional
    public Producto crearProducto(ProductoDto productoDto) {
        Producto producto = Producto.builder()
                .nombre(productoDto.getNombre())
                .precio(productoDto.getPrecio())
                .build();
        return productoRepo.save(producto);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductoDto obtenerProductoPorId(Integer id) {
        Producto producto = productoRepo.findById(id).orElse(null); 
        return producto != null ? ProductoDto.builder()
                .idProducto(producto.getIdProducto())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .build() : null;
    }

    @Override
    @Transactional
    public Void eliminarProducto(Integer id) {
        productoRepo.deleteById(id);
        return null;
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Integer id, ProductoDto productoDto) {
        Producto productoExistente = productoRepo.findById(id).orElse(null);
        if (productoExistente != null) {
            productoExistente.setNombre(productoDto.getNombre());
            productoExistente.setPrecio(productoDto.getPrecio());
            return productoRepo.save(productoExistente);
        }
        return null;
    }

}