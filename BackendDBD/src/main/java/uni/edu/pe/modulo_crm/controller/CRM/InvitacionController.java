package uni.edu.pe.modulo_crm.controller.CRM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.modulo_crm.dto.*;
import uni.edu.pe.modulo_crm.service.InvitacionService;

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

    @GetMapping("/requerimientos/{id_invitacion}")
    public List<MostrarRequerimientos> obtenerRequerimientosCliente(@PathVariable String id_invitacion){
        return service.requerimientosInvitacion(id_invitacion);
    }
    @GetMapping("/mostrarinv/{id_invitacion}")
    public MostrarInvitacion obtenerInvitacion(@PathVariable String id_invitacion){
        return service.obtenerInvitacion(id_invitacion);
    }
    @PutMapping("/actualizarinv")
    public ResponseEntity<?> updateInvitacion(@RequestBody ActualizarInvitacion invitacion){
        try{
            invitacion = service.actualizarinvitacion(invitacion);
            return ResponseEntity.ok().body(invitacion);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error en la actualización de la invitacion", HttpStatus.BAD_REQUEST);
        }
    }
}
