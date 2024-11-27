package uni.edu.pe.modulo_crm.dto.GestionProyectosdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaDTO {
    private String idTarea;
    private String nombreTarea;
    private String fechaInicioTarea;
    private String fechaFinTarea;
    private String estadoTarea;
    private String idFase;
    private String idProyecto;
}
