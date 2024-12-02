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
@CrossOrigin(origins = "http://localhost:4200")
public class CronogramaController {
    @Autowired
    private CronogramasService cronogramasService;

    @GetMapping("/cronogramas")
    public List<ListaCronogramas> obtenerCronogramas() {
        return cronogramasService.obtenerCronogramas();
    }
}