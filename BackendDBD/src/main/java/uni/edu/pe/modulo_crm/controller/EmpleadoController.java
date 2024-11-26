package uni.edu.pe.modulo_crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.edu.pe.modulo_crm.dto.Empleado;
import uni.edu.pe.modulo_crm.service.EmpleadoService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }
    @GetMapping("/empleado")
    public Empleado buscarEmpleadoPorId(@RequestParam String correo,@RequestParam String password) {
        return empleadoService.buscarEmpleado(correo, password);
    }
}
