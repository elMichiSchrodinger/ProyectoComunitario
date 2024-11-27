package uni.edu.pe.modulo_crm.dto.GestionProyectosdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GestionProyectoDTO {
    private String idProyecto;
    private String nombreProyecto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int idEstado;
}
