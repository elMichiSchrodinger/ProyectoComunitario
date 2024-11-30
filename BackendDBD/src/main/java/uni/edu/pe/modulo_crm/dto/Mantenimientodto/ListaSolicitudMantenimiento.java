package uni.edu.pe.modulo_crm.dto.Mantenimientodto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaSolicitudMantenimiento {
    private String idSolicitud;
    private Date fechaSolicitud;
    private String descripcion;
    private String urgencia;
    private String idEmpleado;
    private String idEquipo;
}
