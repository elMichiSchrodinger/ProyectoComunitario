package uni.edu.pe.modulo_crm.dto.GestionProyectosdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformeProyectoDTO {
    private String idInforme;
    private String fechaInforme;
    private String tipoInforme;
    private String contenido;
    private String idProyecto;
    private int progresoPorcentaje;
    private String responsable;
    private String fechaInicioProgramada;
    private String fechaFinProgramada;
    private String riesgosIdentificados;
    private String accionesMitigacion;
}
