package uni.edu.pe.modulo_crm.controller.InspeccionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.edu.pe.modulo_crm.dto.Inspecciondto.MostrarResultadoInspeccion;
import uni.edu.pe.modulo_crm.service.Inspeccionservice.ResultadoInspeccionService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ResultadoInspeccionController {

    private final ResultadoInspeccionService resultadoInspeccionService;

    @Autowired
    public ResultadoInspeccionController(ResultadoInspeccionService resultadoInspeccionService) {
        this.resultadoInspeccionService = resultadoInspeccionService;
    }

    // Endpoint para obtener todos los lotes pendientes de inspección/verificación
    @GetMapping("/mostrarResultadosInspeccion")
    public List<MostrarResultadoInspeccion> obtenerResultadosPendientes() {
        return resultadoInspeccionService.obtenerResultadosPendientes();
    }

    // Endpoint para buscar un lote específico por su ID
    @GetMapping("/buscarResultadoInspeccion")
    public List<Map<String, Object>> buscarResultadoPorLote(@RequestParam String idLote) {
        return resultadoInspeccionService.buscarResultadoPorLote(idLote);
    }
}
