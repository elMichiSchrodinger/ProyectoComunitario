package uni.edu.pe.modulo_crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertarInvitacion {
    private String id_Invitacion;
    private String asunto_Invitacion;
    private Date fecha_Envio;
    private int tiempo_Maximo;
    private String direccion_Proyecto;
    private String comentario;
    private String estado_Invitacion;
    private String id_Cliente;
}
