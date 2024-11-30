package uni.edu.pe.modulo_crm.dto.Mantenimientodto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaEquipos {
    private String idEquipo;
    private String nombre;
    private String tipo;
    private Date adquisicion;
    private String estado;
    private Date ultimaRevision;
    private Date proximaRevision;
}
