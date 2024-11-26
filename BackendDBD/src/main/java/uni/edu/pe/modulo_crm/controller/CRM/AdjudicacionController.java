package uni.edu.pe.modulo_crm.controller.CRM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uni.edu.pe.modulo_crm.dto.InsertarAdjudicacion;
import uni.edu.pe.modulo_crm.service.AdjudicacionService;

@RestController
@CrossOrigin(origins = "*")
public class AdjudicacionController {
    @Autowired
    private AdjudicacionService service;
    @PostMapping("/insertada")
    public ResponseEntity<InsertarAdjudicacion> postadjudicaciona(@RequestBody InsertarAdjudicacion adjudicacion) {
        adjudicacion = service.insertaradjudicaciona(adjudicacion);
        return ResponseEntity.ok(adjudicacion);
    }
    @PostMapping("/insertadr")
    public ResponseEntity<InsertarAdjudicacion> postadjudicacionr(@RequestBody InsertarAdjudicacion adjudicacion) {
        adjudicacion = service.insertaradjudicacionr(adjudicacion);
        return ResponseEntity.ok(adjudicacion);
    }
}
