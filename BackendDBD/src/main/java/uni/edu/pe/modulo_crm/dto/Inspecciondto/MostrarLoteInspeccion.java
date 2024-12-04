package uni.edu.pe.modulo_crm.dto.Inspecciondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostrarLoteInspeccion {
    private String lote;
    private String descripcion_del_lote;
    private Integer cantidad_del_lote;
    private String unidad_de_medida;
    private String estado_inspeccion;
}
