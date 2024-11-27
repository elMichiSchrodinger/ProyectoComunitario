package uni.edu.pe.modulo_crm.controller.GestionProyectosController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.modulo_crm.dto.GestionProyectosdto.GestionProyectoDTO;
import uni.edu.pe.modulo_crm.service.GestionProyectosservice.ProyectoDetalleService;


@RestController
@RequestMapping("/detalleProyectos")
@CrossOrigin(origins = "*")
public class ProyectoDetalleController {

    @Autowired
    private ProyectoDetalleService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProyectoPorId(@PathVariable String id) {
        try {
            GestionProyectoDTO proyecto = service.obtenerProyectoPorId(id);
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado con ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearProyecto(@RequestBody GestionProyectoDTO proyecto) {
        try {
            service.crearProyecto(proyecto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Proyecto creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el proyecto: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProyecto(@PathVariable String id, @RequestBody GestionProyectoDTO proyecto) {
        try {
            service.actualizarProyecto(id, proyecto);
            return ResponseEntity.ok("Proyecto actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar el proyecto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProyecto(@PathVariable String id) {
        try {
            service.eliminarProyecto(id);
            return ResponseEntity.ok("Proyecto eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar el proyecto: " + e.getMessage());
        }
    }
}
