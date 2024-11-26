package uni.edu.pe.modulo_crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertarBeneficios {
    private String id_beneficio;
    private String descrip_beneficio;
    private String id_presentacion_propuesta;
}
