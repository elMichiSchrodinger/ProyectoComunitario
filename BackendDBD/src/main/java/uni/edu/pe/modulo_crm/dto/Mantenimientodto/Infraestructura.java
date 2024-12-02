package uni.edu.pe.modulo_crm.dto.Mantenimientodto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Infraestructura {
    private String idInfraestructura;
    private String nombre;
    private Date fechaAdquisicion;
    private String ubicacion;
    private String estado;
    private String tipo;
    private String responsable;
    private Integer frecuenciaMantenimiento;
    private Date ultimoMantenimiento;
    private Date proximoMantenimiento;
}
