package ApiAvanzado.SistemaInventario.service;

import ApiAvanzado.SistemaInventario.model.entity.Categoria;
import ApiAvanzado.SistemaInventario.model.entity.dto.CategoriaDto;

public interface ICategoria {
    
    Categoria crearCategoria(CategoriaDto categoriaDto);

    Categoria obtenerCategoriaPorId(Integer id);

    Void eliminarCategoria(Integer id);
    
    Categoria actualizarCategoria(Integer id, CategoriaDto categoriaDto);
}
