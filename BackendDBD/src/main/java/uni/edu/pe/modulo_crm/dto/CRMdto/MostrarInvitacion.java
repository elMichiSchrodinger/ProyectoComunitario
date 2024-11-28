package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostrarInvitacion {
    private String id_invitacion;
    private String nombre_cliente;
    private String asunto_invitacion;
    private int tiempo_maximo;
    private String direccion_proyecto;
    private String comentario;
}
