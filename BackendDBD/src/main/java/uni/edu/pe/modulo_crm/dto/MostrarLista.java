package uni.edu.pe.modulo_crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostrarLista {
    private String id_cliente;
    private String id_invitacion;
    private String nombre_cliente;
}
