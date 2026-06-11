package ApiAvanzado.SistemaInventario.service;

import ApiAvanzado.SistemaInventario.model.entity.Producto;
import ApiAvanzado.SistemaInventario.model.entity.dto.ProductoDto;
public interface IProducto {
    
    Producto crearProducto(ProductoDto productoDto);

    ProductoDto obtenerProductoPorId(Integer id);

    Void eliminarProducto(Integer id);
    
    Producto actualizarProducto(Integer id, ProductoDto productoDto);
}
