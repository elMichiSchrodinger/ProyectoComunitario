package uni.edu.pe.modulo_crm.controller.CRMController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.edu.pe.modulo_crm.dto.CRMdto.MostrarLista;
import uni.edu.pe.modulo_crm.service.CRMservice.ListaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ListaController {
    @Autowired
    private ListaService service;
    @GetMapping("/mostrarlist")
    public List<MostrarLista> getlista() {
        return service.mostrarlista();
    }
}