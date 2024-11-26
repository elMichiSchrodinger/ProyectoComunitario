package uni.edu.pe.modulo_crm.controller.CRMController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.edu.pe.modulo_crm.dto.CRMdto.LoginCli;
import uni.edu.pe.modulo_crm.service.CRMservice.LoginCliService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LoginCliController {
    @Autowired
    private LoginCliService loginCliService;

    @GetMapping("/logclientes")
    public List<LoginCli> listarlogincli() {
        return loginCliService.listarlogincli();
    }
    @GetMapping("/logcliente")
    public LoginCli buscarlogincli(@RequestParam String ruc_dni, @RequestParam String correo) {
        return loginCliService.buscarlogincli(ruc_dni,correo);
    }
}
