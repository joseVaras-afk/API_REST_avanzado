package ApiAvanzado.SistemaInventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ApiAvanzado.SistemaInventario.service.IProducto;
import ApiAvanzado.SistemaInventario.repository.ProductoRepo;
import ApiAvanzado.SistemaInventario.model.entity.Producto;
import ApiAvanzado.SistemaInventario.model.entity.dto.ProductoDto;
import jakarta.validation.Valid;
import ApiAvanzado.SistemaInventario.model.payload.MesageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProducto productoService;

    @PostMapping("producto")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductoDto productoDto) {
        try {
            Producto nuevoProducto = productoService.crearProducto(productoDto);
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Producto creado exitosamente")
                    .object(nuevoProducto)
                    .build(), HttpStatus.CREATED);
            } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Error al acceder a la base de datos: " + exDt.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("producto/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@Valid @PathVariable Integer id) {
        try {
            ProductoDto producto = productoService.obtenerProductoPorId(id);
            if (producto != null) {
                return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Producto encontrado")
                        .object(producto)
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Producto no encontrado")
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Error al acceder a la base de datos: " + exDt.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("producto/{id}")
    public ResponseEntity<?> eliminarProducto(@Valid @PathVariable Integer id) {
        ProductoDto producto = productoService.obtenerProductoPorId(id);
        try {
            if (producto == null) {
                return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Producto no encontrado")
                        .build(), HttpStatus.NOT_FOUND);
            } else {
                productoService.eliminarProducto(id);
                return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Producto eliminado exitosamente")
                    .build(), HttpStatus.NO_CONTENT);
            }
            
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Error al acceder a la base de datos: " + exDt.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("producto/{id}")
    public ResponseEntity<?> actualizarProducto(@Valid @PathVariable Integer id, @RequestBody ProductoDto productoDto) {
        try {
            ProductoDto productoExistente = productoService.obtenerProductoPorId(id);
            if (productoExistente!= null) {
              productoService.actualizarProducto(id, productoDto);  
              return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Producto actualizado exitosamente")
                        .object(productoDto)
                        .build(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Producto no encontrado")
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Error al acceder a la base de datos: " + exDt.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}