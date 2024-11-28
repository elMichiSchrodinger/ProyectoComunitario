package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertarInvitacion {
    private String id_invitacion;
    private String asunto_invitacion;
    private Date fecha_envio;
    private int tiempo_maximo;
    private String direccion_proyecto;
    private String comentario;
    private String estado_invitacion;
    private String id_cliente;
}
