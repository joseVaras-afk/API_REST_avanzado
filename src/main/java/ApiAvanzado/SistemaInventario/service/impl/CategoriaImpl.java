package ApiAvanzado.SistemaInventario.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ApiAvanzado.SistemaInventario.service.ICategoria;
import ApiAvanzado.SistemaInventario.repository.CategoriaRepo;
import ApiAvanzado.SistemaInventario.model.entity.Categoria;
import ApiAvanzado.SistemaInventario.model.entity.dto.CategoriaDto;

@Service
public class CategoriaImpl  implements ICategoria {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    @Transactional
    public Categoria crearCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = Categoria.builder()
                .nombre(categoriaDto.getNombre())
                .build();
        return categoriaRepo.save(categoria);
    }

    @Transactional(readOnly = true)
    @Override
    public Categoria obtenerCategoriaPorId(Integer id) {
        return categoriaRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Void eliminarCategoria(Integer id) {
        categoriaRepo.deleteById(id);
        return null;
    }

    @Override
    @Transactional
    public Categoria actualizarCategoria(Integer id, CategoriaDto categoriaDto) {
        Categoria categoriaExistente = categoriaRepo.findById(id).orElse(null);
        if (categoriaExistente != null) {
            categoriaExistente.setNombre(categoriaDto.getNombre());
            return categoriaRepo.save(categoriaExistente);
        }
        return null;
    }
    
}
