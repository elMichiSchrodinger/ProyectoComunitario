package uni.edu.pe.modulo_crm.dto.Inspecciondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostrarResultadoInspeccion {
    private String lote;
    private String descripcion_del_lote;
    private String estado_verificacion;
    private String estado_inspeccion;
    private String estado_final;
}

