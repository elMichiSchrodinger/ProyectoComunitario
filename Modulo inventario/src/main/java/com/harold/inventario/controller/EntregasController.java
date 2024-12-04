package com.harold.inventario.controller;

import com.harold.inventario.model.DetalleGuia;
import com.harold.inventario.model.GuiaRemision;
import com.harold.inventario.service.GuiaRemisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class EntregasController {

    @Autowired
    private GuiaRemisionService guiaRemisionService;

    // Método para la página principal de entregas
    @GetMapping("/entregas")
    public String entregas(Model model) {
        List<GuiaRemision> guias = guiaRemisionService.ObtenerGuiaRemisionEntrega();
        model.addAttribute("guias", guias);
        return "entregas";
    }

    // Método para la página de detalle de entrega
    @GetMapping("/detalle_entrega")
    public String detalleEntrega(@RequestParam(value = "id") String idGuiaRemision, Model model) {
        GuiaRemision guiaRemision = guiaRemisionService.ObtenerGuiaRemisionCompleta(idGuiaRemision);

        if (guiaRemision == null) {
            model.addAttribute("detalle", null);
            model.addAttribute("detalleGuias", null);
        } else {
            model.addAttribute("detalle", guiaRemision);
            model.addAttribute("detalleGuias", guiaRemision.getDetalleGuias());
        }

        return "detalle_entrega";
    }

    // Método para la página de edición de entrega
    @GetMapping("/editar_entrega")
    public String editarEntrega(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null && !id.equals("new")) {
            // Si se pasa un ID válido, obtenemos la guía de remisión existente con sus detalles
            GuiaRemision guiaRemision = guiaRemisionService.ObtenerGuiaRemisionCompleta(id);
            model.addAttribute("guiaRemision", guiaRemision);
        } else {
            // Si no hay ID o es nuevo, solo mostramos los campos vacíos
            model.addAttribute("guiaRemision", new GuiaRemision());
        }
        return "editar_entrega"; // Redirige a editar_entrega.html
    }

    @PostMapping("/editar_entrega")
    public String saveChanges(@ModelAttribute GuiaRemision guiaRemision, Model model) {
        try {
            // Verificamos si la guía ya existe o si es una nueva
            if (guiaRemision.getIdGuiaRemision() != null && !guiaRemision.getIdGuiaRemision().isEmpty()) {
                // Si es una guía existente, actualizamos
                guiaRemisionService.actualizarGuiaRemision(guiaRemision);
                guiaRemisionService.guardarDetalles(new ArrayList<>(guiaRemision.getDetalleGuias()), guiaRemision.getIdGuiaRemision());
                model.addAttribute("success", "Guía y detalles actualizados correctamente.");
            } else {
                // Si la guía es nueva, creamos una nueva guía
                guiaRemision = guiaRemisionService.crearGuiaConDetalles(guiaRemision);
                model.addAttribute("success", "Guía de entrega creada exitosamente.");
            }
            // Al finalizar, redirigimos a la vista de detalles de la guía
            return "detalle_entrega";  // Redirige a detalle_entrega.html con los cambios
        } catch (Exception e) {
            // Si ocurre un error, mostramos el mensaje de error
            model.addAttribute("error", "Error al guardar los cambios: " + e.getMessage());
            return "editar_entrega";  // Vuelve a la vista de edición en caso de error
        }
    }



    // Método para guardar la guía de remisión de entrega (actualización o creación)
    @PostMapping("/guardar_entrega")
    public String guardarEntrega(@ModelAttribute GuiaRemision guiaRemision, Model model) {
        try {
            if (guiaRemision.getIdGuiaRemision() == null || guiaRemision.getIdGuiaRemision().isEmpty()) {
                // Si el ID de la guía es null, estamos creando una nueva guía
                guiaRemision = guiaRemisionService.crearGuiaConDetalles(guiaRemision);
                model.addAttribute("success", "Guía de entrega creada exitosamente.");
            } else {
                // Si el ID está presente, se trata de una actualización
                guiaRemisionService.actualizarGuiaRemision(guiaRemision);
                model.addAttribute("success", "Guía de entrega actualizada exitosamente.");
            }
            return "redirect:/entregas";  // Redirige a la lista de entregas
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la entrega: " + e.getMessage());
            return "editar_entrega";  // Vuelve a la vista de edición en caso de error
        }
    }

    // Método para eliminar una entrega por ID
    @PostMapping("/eliminar_entrega")
    public ResponseEntity<Map<String, String>> eliminarEntrega(@RequestParam("id") String id) {
        Map<String, String> response = new HashMap<>();
        try {
            // Llamamos al servicio para eliminar la guía de remisión
            guiaRemisionService.eliminarGuias(List.of(id));
            response.put("message", "Guía de entrega eliminada correctamente.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", "Error al eliminar la guía de entrega.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Reemplazar el mapeo con un mapeo único para no conflicto con el de RecepcionesController
    @PostMapping("/api/guias/eliminarEntrega")
    @ResponseBody
    public ResponseEntity<String> eliminarGuiasEntrega(@RequestBody List<String> idsGuiaRemision) {
        try {
            if (idsGuiaRemision == null || idsGuiaRemision.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se proporcionaron guías para eliminar.");
            }

            // Llamar al servicio para eliminar las guías y sus detalles
            guiaRemisionService.eliminarGuias(idsGuiaRemision);

            return ResponseEntity.ok("Guías de entrega y detalles eliminados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar las guías de entrega o sus detalles: " + e.getMessage());
        }
    }
}
