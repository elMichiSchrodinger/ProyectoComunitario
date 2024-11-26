package uni.edu.pe.modulo_crm.controller.CRM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.modulo_crm.dto.*;
import uni.edu.pe.modulo_crm.service.PropuestaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PropuestaController {
    @Autowired
    private PropuestaService service;
    @GetMapping("/mostrarpro")
    public List<MostrarPropuesta> getpropuesta(){
        return service.mostrarpropuesta();
    }
    @GetMapping("/mostrarga")
    public List<MostrarGarantias> getgarantia(){
        return service.mostrargarantias();
    }
    @GetMapping("/mostrarbe")
    public List<MostrarBeneficios> getbeneficio(){
        return service.mostrarbeneficios();
    }
    @PostMapping("/insertpro")
    public ResponseEntity<InsertarPropuesta> postpropuesta(@RequestBody InsertarPropuesta propuesta) {
        propuesta = service.insertarpropuesta(propuesta);
        return ResponseEntity.ok(propuesta);
    }
    @PostMapping("/insertga")
    public ResponseEntity<InsertarGarantias> postgarantia(@RequestBody InsertarGarantias garantia) {
        garantia = service.insertargarantia(garantia);
        return ResponseEntity.ok(garantia);
    }
    @PostMapping("/insertbe")
    public ResponseEntity<InsertarBeneficios> postbeneficio(@RequestBody InsertarBeneficios beneficio) {
        beneficio = service.insertarbeneficio(beneficio);
        return ResponseEntity.ok(beneficio);
    }
    @PutMapping("/actualizarpro")
    public ResponseEntity<?> updatePropuesta(@RequestBody ActualizarPropuesta propuesta){
        try{
            propuesta = service.actualizarpropuesta(propuesta);
            return ResponseEntity.ok().body(propuesta);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error en la actualización de la propuesta", HttpStatus.BAD_REQUEST);
        }
    }
}
