package uni.edu.pe.modulo_crm.controller.CRMController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.modulo_crm.dto.CRMdto.*;
import uni.edu.pe.modulo_crm.service.CRMservice.InvitacionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class InvitacionController {
    @Autowired
    private InvitacionService service;
    @GetMapping("/mostrarinv")
    public List<MostrarInvitacion> getinvitacion() {
        return service.mostrarinvitacion();
    }
    @GetMapping("/mostrarreq")
    public List<MostrarRequerimientos> getrequerimientos() {
        return service.mostrarrequerimientos();
    }
    @PostMapping("/insertinv")
    public ResponseEntity<InsertarInvitacion> postinvitacion(@RequestBody InsertarInvitacion invitacion) {
        invitacion = service.insertarinvitacion(invitacion);
        return ResponseEntity.ok(invitacion);
    }
    @PostMapping("/insertreq")
    public ResponseEntity<InsertarRequerimientos> postrequerimientos(@RequestBody InsertarRequerimientos requerimiento) {
        requerimiento = service.insertarrequerimiento(requerimiento);
        return ResponseEntity.ok(requerimiento);
    }

    @GetMapping("/mostrarreq/{id_invitacion}")
    public List<MostrarRequerimientos> obtenerrequerimientos(@PathVariable String id_invitacion){
        return service.requerimientosinvitacion(id_invitacion);
    }
    @GetMapping("/mostrarinv/{id_invitacion}")
    public MostrarInvitacion obtenerinvitacion(@PathVariable String id_invitacion){
        return service.obtenerinvitacion(id_invitacion);
    }
    @PutMapping("/rechazarinv")
    public ResponseEntity<?> rechazarinvitacion(@RequestBody ActualizarInvitacion invitacion){
        try{
            invitacion = service.rechazarinvitacion(invitacion);
            return ResponseEntity.ok().body(invitacion);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error en la actualización de la invitacion", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/aceptarinv")
    public ResponseEntity<?> aceptarinvitacion(@RequestBody ActualizarInvitacion invitacion){
        try{
            invitacion = service.aceptarinvitacion(invitacion);
            return ResponseEntity.ok().body(invitacion);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error en la actualización de la invitacion", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/completarinv")
    public ResponseEntity<?> completarinvitacion(@RequestBody ActualizarInvitacion invitacion){
        try{
            invitacion = service.completarinvitacion(invitacion);
            return ResponseEntity.ok().body(invitacion);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error en la actualización de la invitacion", HttpStatus.BAD_REQUEST);
        }
    }
}
