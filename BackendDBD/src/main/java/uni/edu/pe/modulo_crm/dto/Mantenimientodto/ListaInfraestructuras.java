package uni.edu.pe.modulo_crm.dto.Mantenimientodto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaInfraestructuras {
    private String idInfraestructura;
    private String nombre;
    private String fechaAdquisicion;
    private String ubicacion;
    private String estado;
    private String tipo;
    private Date ultimoMantenimiento;
    private String proximoMantenimiento;
}
