package uni.edu.pe.modulo_crm.dto.Mantenimientodto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cronogramas {
    private String idCronograma;
    private String nombreEquipoInfraestructura;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private String descripcion;
}
