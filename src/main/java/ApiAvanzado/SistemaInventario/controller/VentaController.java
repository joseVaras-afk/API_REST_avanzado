package ApiAvanzado.SistemaInventario.controller;

import org.hibernate.validator.cfg.defs.pl.REGONDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ApiAvanzado.SistemaInventario.service.IVentas;
import ApiAvanzado.SistemaInventario.repository.VentaRepo;
import ApiAvanzado.SistemaInventario.model.entity.Venta;
import ApiAvanzado.SistemaInventario.model.entity.dto.VentaDto;
import jakarta.validation.Valid;
import ApiAvanzado.SistemaInventario.model.payload.MesageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private IVentas ventaService;

    @PostMapping("venta")
    public ResponseEntity<?> crearVenta(@Valid @RequestBody VentaDto ventaDto ){

            Venta nuevaVenta = ventaService.crearVenta(ventaDto);

            return new ResponseEntity<>(
                MesageResponse.builder()
                .message("venta registrada con exito")
                .object(nuevaVenta)
                .build(),HttpStatus.CREATED);
            
    }

    @GetMapping("venta/{id}")
    public ResponseEntity<?> buscarVenta(@Valid @PathVariable Integer id){
        VentaDto venta = ventaService.obtenerVentaPorId(id);

        return new ResponseEntity<>(MesageResponse.builder()
                .message("Venta encontrada")    
                .object(venta).build(), HttpStatus.OK);
    }

    @DeleteMapping("venta/{id}")
    public ResponseEntity<?> eliminarVenta(@Valid @PathVariable Integer id){
        VentaDto venta = ventaService.obtenerVentaPorId(id);

        try {
            if (venta == null) {
                return new ResponseEntity<>(MesageResponse
                        .builder()
                        .message("Id de venta incorrecto")
                        .build(), HttpStatus.NOT_FOUND);
                
            }else {
                ventaService.eliminarVenta(id);
                return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Venta eliminada")
                    .build(), HttpStatus.NO_CONTENT);
            }
            
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MesageResponse
                    .builder()
                    .message("Error al acceder a la base de datos: " + exDt.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
