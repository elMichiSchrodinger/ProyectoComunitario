package uni.edu.pe.modulo_crm.controller.MantenimientoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.Cronogramas;
import uni.edu.pe.modulo_crm.service.Mantenimientoservice.CronogramaService;

import java.util.List;

@RestController
@RequestMapping("/cmms")
@CrossOrigin(origins = "*")
public class CronogramaController {
    @Autowired
    private CronogramaService cronogramaService;

    @GetMapping("/cronogramas")
    public List<Cronogramas> obtenerCronogramas() {
        return cronogramaService.obtenerCronogramas();
    }
}