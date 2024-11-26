package uni.edu.pe.modulo_crm.controller.CRMController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.modulo_crm.dto.InsertarRevision;
import uni.edu.pe.modulo_crm.dto.MostrarStock;
import uni.edu.pe.modulo_crm.service.RevisionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RevisionController {
    @Autowired
    private RevisionService service;
    @GetMapping("/mostrarstock")
    public List<MostrarStock> getstock() {
        return service.mostrarstock();
    }
    @PostMapping("/insertreva")
    public ResponseEntity<InsertarRevision> postrevisiona(@RequestBody InsertarRevision revision) {
        revision = service.insertarrevisiona(revision);
        return ResponseEntity.ok(revision);
    }
    @PostMapping("/insertrevr")
    public ResponseEntity<InsertarRevision> postrevisionr(@RequestBody InsertarRevision revision) {
        revision = service.insertarrevisionr(revision);
        return ResponseEntity.ok(revision);
    }
}
