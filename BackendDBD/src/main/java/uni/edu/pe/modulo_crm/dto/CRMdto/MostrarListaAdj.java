package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostrarListaAdj {
    private String id_cliente;
    private String id_adjudicacion;
    private String nombre_cliente;
    private String estado_adjudicacion;
}
