package uni.edu.pe.modulo_crm.dto.GestionProyectosdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaseDTO {
    private String idFase;
    private String nombreFase;
    private String fechaInicioFase;
    private String fechaFinFase;
    private String idProyecto;
}
