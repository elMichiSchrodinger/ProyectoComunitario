package uni.edu.pe.modulo_crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @NoArgsConstructor @AllArgsConstructor
public class Empleado {
    private String id_empleado;
    private String nombre;
    private String apellido;
    private Date fecha_contratacion;
    private String tipo_empleado;
    private double salario;
    private String departamento;
    private String telefono;
    private String correo;
    private String dni;
    private String direccion;
    private String password;
}
