package uni.edu.pe.modulo_crm.controller.MantenimientoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.Equipo;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.ListaEquipos;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.ListaSolicitudMantenimiento;
import uni.edu.pe.modulo_crm.service.Mantenimientoservice.EquipoService;

import java.util.List;

@RestController
@RequestMapping("/cmms")
@CrossOrigin(origins = "*")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @GetMapping("/equipos")
    public List<ListaEquipos> obtenerEquipos() {
        return equipoService.obtenerEquipos(); // Devuelve una lista de ListaEquipos
    }

    @GetMapping("equipos/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable String id) {
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        return ResponseEntity.ok(equipo);
    }

    @PostMapping("/solicitar-mantenimiento")
    public ResponseEntity<String> crearSolicitudMantenimiento(@RequestBody ListaSolicitudMantenimiento solicitud) {
        equipoService.solicitarMantenimiento(solicitud);
        return ResponseEntity.ok("Solicitud de mantenimiento creada exitosamente.");
    }
}