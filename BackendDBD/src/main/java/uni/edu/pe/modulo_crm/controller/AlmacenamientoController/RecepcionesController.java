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
public class RecepcionesController {

    @Autowired
    private GuiaRemisionService guiaRemisionService;

    // Método para la página principal de recepciones
    @GetMapping("/recepciones")
    public String recepciones(Model model) {
        List<GuiaRemision> guias = guiaRemisionService.ObtenerGuiaRemisionRecepcion();
        model.addAttribute("guias", guias);
        return "recepciones";
    }

    // Método para la página de detalle de recepción
    @GetMapping("/detalle_recepcion")
    public String detalleRecepcion(@RequestParam(value = "id") String idGuiaRemision, Model model) {
        GuiaRemision guiaRemision = guiaRemisionService.ObtenerGuiaRemisionCompleta(idGuiaRemision);

        if (guiaRemision == null) {
            model.addAttribute("detalle", null);
            model.addAttribute("detalleGuias", null);
        } else {
            model.addAttribute("detalle", guiaRemision);
            model.addAttribute("detalleGuias", guiaRemision.getDetalleGuias());
        }

        return "detalle_recepcion";
    }

    // Método para la página de edición
    @GetMapping("/editar_recepcion")
    public String editarRecepcion(@RequestParam(value = "id", required = false) String id, Model model) {
        model.addAttribute("id", id);
        return "editar_recepcion";
    }

    // Endpoint API para obtener detalle en formato JSON
    @GetMapping("/api/guias/detalle")
    @ResponseBody
    public GuiaRemision obtenerDetalle(@RequestParam String id) {
        GuiaRemision guia = guiaRemisionService.ObtenerGuiaRemisionCompleta(id);
        if (guia == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guía no encontrada");
        }
        return guia;
    }

    // Modificación de la Guía y los Detalles de la Guía
    // Ruta para editar la guía con los detalles
    @PostMapping("/api/guias/editar")
    public ResponseEntity<Map<String, String>> editarGuia(@RequestBody GuiaRemision guiaRemision) {
        try {
            // Verificamos si la guía ya existe
            if (guiaRemision.getIdGuiaRemision() == null || guiaRemision.getIdGuiaRemision().isEmpty()) {
                // Si el ID de la guía es null o vacío, significa que es una nueva guía
                // Primero creamos la guía de remisión
                GuiaRemision guiaCreada = guiaRemisionService.crearGuiaConDetalles(guiaRemision);

                // Luego, guardamos los detalles de la guía con el nuevo ID
                if (guiaRemision.getDetalleGuias() != null && !guiaRemision.getDetalleGuias().isEmpty()) {
                    guiaRemisionService.guardarDetalles(new ArrayList<>(guiaRemision.getDetalleGuias()), guiaCreada.getIdGuiaRemision());
                }

                // Respuesta exitosa con el id de la nueva guía
                Map<String, String> response = new HashMap<>();
                response.put("status", "success");
                response.put("message", "Guía creada y detalles guardados exitosamente.");
                response.put("idGuiaRemision", guiaCreada.getIdGuiaRemision());

                return ResponseEntity.ok(response);

            } else {
                // Si la guía ya existe, actualizamos
                guiaRemisionService.actualizarGuiaRemision(guiaRemision);

                // Guardamos o actualizamos los detalles de la guía
                if (guiaRemision.getDetalleGuias() != null && !guiaRemision.getDetalleGuias().isEmpty()) {
                    guiaRemisionService.guardarDetalles(new ArrayList<>(guiaRemision.getDetalleGuias()), guiaRemision.getIdGuiaRemision());
                }

                // Respuesta exitosa con el id de la guía
                Map<String, String> response = new HashMap<>();
                response.put("status", "success");
                response.put("message", "Cambios guardados exitosamente.");
                response.put("idGuiaRemision", guiaRemision.getIdGuiaRemision());

                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Error al actualizar la guía: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }



    @DeleteMapping("/api/guias/eliminarDetalles")
    public ResponseEntity<String> eliminarDetalles(@RequestBody List<String> idsDetalle) {
        try {
            // Llamar al servicio para eliminar los detalles
            guiaRemisionService.eliminarDetalles(idsDetalle);

            return ResponseEntity.ok("Detalles eliminados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar los detalles: " + e.getMessage());
        }
    }

    @PostMapping("/api/guias/eliminar")
    @ResponseBody
    public ResponseEntity<String> eliminarGuias(@RequestBody List<String> idsGuiaRemision) {
        try {
            if (idsGuiaRemision == null || idsGuiaRemision.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se proporcionaron guías para eliminar.");
            }

            // Llamar al servicio para eliminar las guías y sus detalles
            guiaRemisionService.eliminarGuias(idsGuiaRemision);

            return ResponseEntity.ok("Guías y detalles eliminados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar las guías o sus detalles: " + e.getMessage());
        }
    }

    @PostMapping("/api/guias/crear")
    public ResponseEntity<Map<String, String>> crearGuia(@RequestBody GuiaRemision guiaRemision) {
        try {
            // Crear la guía de remisión
            GuiaRemision guiaCreada = guiaRemisionService.crearGuiaConDetalles(guiaRemision);

            // Confirmar creación y devolver el ID de la nueva guía
            Map<String, String> response = new HashMap<>();
            response.put("idGuiaRemision", guiaCreada.getIdGuiaRemision());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Devolver mensaje de error como JSON en caso de excepcion
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al crear la guía de remisión: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
