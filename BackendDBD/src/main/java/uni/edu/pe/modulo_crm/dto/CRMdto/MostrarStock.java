package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostrarStock {
    private String id_informe_stock;
    private int stock_disponible;
}
