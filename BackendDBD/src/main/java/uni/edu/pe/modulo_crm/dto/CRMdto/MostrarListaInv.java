package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostrarListaInv {
    private String id_cliente;
    private String id_invitacion;
    private String nombre_cliente;
    private String estado_invitacion;
    private String id_revision_tecnica;
}
