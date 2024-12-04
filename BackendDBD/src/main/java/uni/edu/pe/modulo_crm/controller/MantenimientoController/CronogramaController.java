package uni.edu.pe.modulo_crm.controller.MantenimientoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uni.edu.pe.modulo_crm.dto.Mantenimientodto.Cronograma;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.ListaCronogramas;
import uni.edu.pe.modulo_crm.service.Mantenimientoservice.CronogramasService;

import java.util.List;

@RestController
@RequestMapping("/cmms")
@CrossOrigin(origins = "*")
public class CronogramaController {
    @Autowired
    private CronogramasService cronogramasService;

    @GetMapping("/cronogramas")
    public List<ListaCronogramas> obtenerCronogramas() {
        return cronogramasService.obtenerCronogramas();
    }

    @PutMapping("/cronogramas/{id}") // Ruta para actualizar el estado
    public ResponseEntity<String> actualizarEstado(@PathVariable String id) {
        cronogramasService.actualizarEstado(id);
        return ResponseEntity.ok("Cambiado a Completado ");
    }
}