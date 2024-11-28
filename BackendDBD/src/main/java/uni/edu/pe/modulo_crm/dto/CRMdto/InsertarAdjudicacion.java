package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertarAdjudicacion {
    private String id_adjudicacion;
    private Date fecha_adjudicacion;
    private String estado_adjudicacion;
    private String id_presentacion_propuesta;
    private String id_cliente;
}
