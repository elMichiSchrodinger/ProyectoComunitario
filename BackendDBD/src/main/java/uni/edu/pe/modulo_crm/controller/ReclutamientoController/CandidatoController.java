package uni.edu.pe.modulo_crm.controller.ReclutamientoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.modulo_crm.dto.Reclutamientodto.Candidato;
import uni.edu.pe.modulo_crm.dto.Reclutamientodto.DetalleCandidato;
import uni.edu.pe.modulo_crm.service.Reclutamientoservice.CandidatoService;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
@CrossOrigin(origins = "*")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;
    @GetMapping("/aprobados")
    public List<DetalleCandidato> getCandidatosAprobados(@RequestParam(required = false) String nombre) {
        return candidatoService.listarCandidatoAprobado(nombre);
    }
    @GetMapping("/aprobados/{id_postulacion}")
    public DetalleCandidato getCandidatoAprobado(@PathVariable String id_postulacion) {
        return candidatoService.candidato(id_postulacion);
    }

    @GetMapping("/desaprobados")
    public List<DetalleCandidato> getCandidatosDesaprobados(@RequestParam(required = false) String nombre) {
        return candidatoService.listarCandidatoDesaprobado(nombre);
    }
    @GetMapping("/desaprobados/{id_postulacion}")
    public DetalleCandidato getCandidatoDesaprobado(@PathVariable String id_postulacion) {
        return candidatoService.candidato(id_postulacion);
    }
    @PostMapping("/crear")
    public ResponseEntity<Candidato> crearCandidato(@RequestBody Candidato candidato){
        candidato = candidatoService.insertarCandidato(candidato);
        return ResponseEntity.ok(candidato);
    }
}
