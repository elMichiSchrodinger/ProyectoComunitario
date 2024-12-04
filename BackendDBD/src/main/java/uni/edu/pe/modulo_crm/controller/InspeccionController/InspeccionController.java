package uni.edu.pe.modulo_crm.controller.InspeccionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.edu.pe.modulo_crm.service.Inspeccionservice.InspeccionService;
import uni.edu.pe.modulo_crm.dto.Inspecciondto.MostrarLoteInspeccion;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class InspeccionController {

    private final InspeccionService inspeccionService;

    @Autowired
    public InspeccionController(InspeccionService inspeccionService) {
        this.inspeccionService = inspeccionService;
    }

    // Endpoint para mostrar lotes con inspecciones pendientes
    @GetMapping("/mostrarLotesPendientes")
    public List<MostrarLoteInspeccion> obtenerLotesPendientes() {
        return inspeccionService.obtenerLotesPendientes();
    }
}
