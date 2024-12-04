package com.harold.inventario.controller;

import com.harold.inventario.service.InformeStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InformeStockViewController {

    @Autowired
    private InformeStockService informeStockService;

    // Ruta para ver todos los informes de stock
    @GetMapping("/informe_stock")
    public String informesStock(Model model) {
        model.addAttribute("informes", informeStockService.ObtenerInformeStock());
        return "informe_stock"; // Nombre del archivo HTML que se mostrará
    }

    // Ruta para ver los detalles de un informe específico
    @GetMapping("/detalle_informe_stock")
    public String detalleInformeStock(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("informe", informeStockService.obtenerDetalleInformeStock(id));
        return "detalle_informe_stock"; // Nombre del archivo HTML con los detalles
    }

    // Ruta para ver los detalles de las órdenes asociadas a un informe
    @GetMapping("/detalle_orden_por_informe_stock")
    public String detalleOrdenPorInformeStock(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("detallesOrden", informeStockService.obtenerDetalleOrdenPorInformeStock(id));
        return "detalle_orden_informe_stock"; // Nombre del archivo HTML con los detalles de las órdenes
    }

    @GetMapping("/editar_informe_stock")
    public String editarInformeStock(@RequestParam(value = "id", required = false) String id, Model model) {
        // Pasa el parámetro 'id' a la vista
        model.addAttribute("id", id);
        return "editar_informe_stock";  // Redirige a editar_informe_stock.html
    }


}
