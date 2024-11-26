package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginCli {
    private String id_cliente;
    private String nombre;
    private String ruc_dni;
    private String direccion;
    private String telefono;
    private String correo;
    private String tipo_cliente;
    private Date fecha_registo;
    private String estado_cliente;
    private String contacto_principal;
    private String sector_economico;
    private String id_empleado;
}
