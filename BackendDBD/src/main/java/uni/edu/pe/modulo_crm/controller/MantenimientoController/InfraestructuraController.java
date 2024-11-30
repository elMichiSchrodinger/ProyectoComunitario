package uni.edu.pe.modulo_crm.controller.MantenimientoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.Infraestructura;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.ListaInfraestructuras;
import uni.edu.pe.modulo_crm.service.Mantenimientoservice.InfraestructuraService;

import java.util.List;

@RestController
@RequestMapping("/cmms")
@CrossOrigin(origins = "*")
public class InfraestructuraController {
    @Autowired
    private InfraestructuraService infraestructuraService;

    @GetMapping("/infraestructuras")
    public List<ListaInfraestructuras> obtenerInfraestructuras() {
        return infraestructuraService.obtenerInfraestructuras(); // Devuelve una lista de ListaInfraestructuras
    }

    @GetMapping("infraestructuras/{id}")
    public ResponseEntity<Infraestructura> obtenerInfraestructuraPorId(@PathVariable String id) {
        Infraestructura infraestructura = infraestructuraService.obtenerInfraestructuraPorId(id);
        return ResponseEntity.ok(infraestructura);
    }
}