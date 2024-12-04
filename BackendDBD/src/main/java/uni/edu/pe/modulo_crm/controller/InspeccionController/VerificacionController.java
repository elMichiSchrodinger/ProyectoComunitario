package uni.edu.pe.modulo_crm.controller.InspeccionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.edu.pe.modulo_crm.service.Inspeccionservice.VerificacionService;
import uni.edu.pe.modulo_crm.dto.Inspecciondto.MostrarLoteVerificacion;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class VerificacionController {

    private final VerificacionService verificacionService;

    @Autowired
    public VerificacionController(VerificacionService verificacionService) {
        this.verificacionService = verificacionService;
    }

    // Endpoint para obtener los lotes pendientes de verificación
    @GetMapping("/mostrarLotesVerificacion")
    public List<MostrarLoteVerificacion> obtenerLotesPendientesVerificacion() {
        return verificacionService.obtenerLotesPendientesVerificacion();
    }

    // Endpoint para buscar un lote específico por su ID de verificación
    @GetMapping("/buscarLoteVerificacion")
    public List<Map<String, Object>> buscarLoteVerificacion(@RequestParam String idLote) {
        return verificacionService.obtenerDetallesLoteVerificacion(idLote);
    }
}
