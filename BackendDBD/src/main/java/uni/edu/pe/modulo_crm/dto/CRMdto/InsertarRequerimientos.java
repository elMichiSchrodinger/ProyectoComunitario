package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertarRequerimientos {
    private String id_requerimiento;
    private String descrip_requerimiento;
    private String id_invitacion;
}
