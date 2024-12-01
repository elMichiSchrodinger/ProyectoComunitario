package uni.edu.pe.modulo_crm.controller.CRMController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uni.edu.pe.modulo_crm.dto.CRMdto.SeguimientoInv;
import uni.edu.pe.modulo_crm.service.CRMservice.SeguimientoInvService;

@RestController
@CrossOrigin(origins = "*")
public class SeguimientoInvController {
    @Autowired
    private SeguimientoInvService service;
    @GetMapping("/mostrarseguimientoinv/{id_cliente}")
    public SeguimientoInv getseguimientoinv(@PathVariable String id_cliente) {
        return service.seguimientoinv(id_cliente);
    }
}
