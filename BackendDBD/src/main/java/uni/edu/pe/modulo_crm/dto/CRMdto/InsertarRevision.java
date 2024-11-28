package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertarRevision {
    private String id_revision_tecnica;
    private String estado_participacion;
    private String id_empleado;
    private String id_invitacion;
    private String id_informe_stock;
}
