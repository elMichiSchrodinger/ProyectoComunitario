package com.harold.inventario.controller;

import com.harold.inventario.model.Recurso;
import com.harold.inventario.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class ReabastecimientoController {

    @Autowired
    private RecursoService recursoService;

    // Método GET para obtener todos los recursos
    @GetMapping("/reabastecimiento")
    public String reabastecimientos(Model model) {
        List<Recurso> recursos = recursoService.ObtenerRecursos();
        model.addAttribute("recursos", recursos);
        return "reabastecimiento";
    }

    // Método GET para obtener el detalle de un recurso
    @GetMapping("/detalle_reabastecimiento")
    public String detalleReabastecimiento(@RequestParam(value = "id", required = false) String id, Model model) {
        model.addAttribute("id", id);
        return "detalle_reabastecimiento";
    }

    // Método GET para editar un recurso
    @GetMapping("/editar_reabastecimiento")
    public String editarReabastecimiento(@RequestParam(value = "id", required = false) String id, Model model) {
        model.addAttribute("id", id);
        return "editar_reabastecimiento";
    }

    // Método para obtener un recurso por su ID
    @GetMapping("/reabastecimiento/recurso/{id}")
    @ResponseBody
    public ResponseEntity<Recurso> obtenerRecursoPorId(@PathVariable String id) {
        Recurso recurso = recursoService.obtenerRecursoPorId(id);
        if (recurso != null) {
            return ResponseEntity.ok(recurso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para obtener las guías de remisión asociadas a un recurso
    @GetMapping("/reabastecimiento/guias_recurso/{idRecurso}")
    public ResponseEntity<List<String>> obtenerGuiasPorRecurso(@PathVariable String idRecurso) {
        List<String> guias = recursoService.obtenerGuiasPorRecurso(idRecurso);
        if (guias.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guias);
        }
    }

    // Método PUT para actualizar los detalles de un recurso
    @PutMapping("/reabastecimiento/recurso/{idRecurso}")
    @ResponseBody
    public ResponseEntity<String> actualizarRecurso(@PathVariable String idRecurso, @RequestBody Recurso recurso) {
        try {
            boolean actualizado = recursoService.actualizarRecurso(idRecurso, recurso);
            if (actualizado) {
                return ResponseEntity.ok("Recurso actualizado exitosamente");
            } else {
                return ResponseEntity.status(400).body("Error al actualizar el recurso");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }
}
