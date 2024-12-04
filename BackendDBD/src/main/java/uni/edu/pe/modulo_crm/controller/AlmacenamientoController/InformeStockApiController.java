package com.harold.inventario.controller;

import com.harold.inventario.model.InformeStock;
import com.harold.inventario.service.InformeStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController  // Usamos @RestController para que las respuestas sean JSON
@RequestMapping("/api")  // Prefijo común para las rutas de la API
public class InformeStockApiController {

    @Autowired
    private InformeStockService informeStockService;

    // Obtener todos los informes de stock
    @GetMapping("/informes_stock")
    public List<InformeStock> getInformesStock() {
        return informeStockService.ObtenerInformeStock();
    }

    // Obtener detalle de un informe de stock
    @GetMapping("/detalle_informe_stock")
    public InformeStock getDetalleInformeStock(@RequestParam(value = "id") String id) {
        return informeStockService.obtenerDetalleInformeStock(id);
    }

    // Obtener detalle de órdenes por informe de stock
    @GetMapping("/detalle_orden_por_informe_stock")
    public List<Map<String, Object>> getDetalleOrdenPorInformeStock(@RequestParam(value = "id") String id) {
        return informeStockService.obtenerDetalleOrdenPorInformeStock(id);
    }

    // Actualizar un informe de stock
    @PutMapping("/informes_stock/{id}")
    public ResponseEntity<String> actualizarInformeStock(
            @PathVariable("id") String idInformeStock,
            @RequestBody InformeStock informeStock) {
        try {
            // Llamamos al servicio para actualizar el informe
            informeStockService.actualizarInformeStock(idInformeStock, informeStock);
            return new ResponseEntity<>("Informe actualizado con éxito", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el informe", HttpStatus.BAD_REQUEST);
        }
    }
}
