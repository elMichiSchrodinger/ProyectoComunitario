package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostrarInvitacion {
    private String id_invitacion;
    private String Nombre_Cliente;
    private String Asunto_Invitacion;
    private int Tiempo_Maximo;
    private String Direccion_Proyecto;
    private String Comentario;
}
