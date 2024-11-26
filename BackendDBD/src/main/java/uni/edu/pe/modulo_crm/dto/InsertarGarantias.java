package uni.edu.pe.modulo_crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertarGarantias {
    private String id_garantia;
    private String descrip_garantia;
    private String id_presentacion_propuesta;
}
