package uni.edu.pe.modulo_crm.controller.GestionProyectosController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.edu.pe.modulo_crm.dto.GestionProyectosdto.GestionProyectoDTO;
import uni.edu.pe.modulo_crm.service.GestionProyectosservice.GestionProyectosService;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class GestionProyectosController {
    @Autowired
    private GestionProyectosService service;

    @GetMapping
    public List<GestionProyectoDTO> obtenerProyectos() {
        return service.mostrarProyectos();
    }
}
