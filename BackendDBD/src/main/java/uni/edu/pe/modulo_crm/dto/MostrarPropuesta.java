package uni.edu.pe.modulo_crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostrarPropuesta {
    private String id_presentacion_propuesta;
    private float precio_propuesto;
    private String descripcion_tecnica;
    private String descripcion_economica;
    private String calidad_ofrecida;
    private String seguridad_ofrecida;
    private String condicion_pago;
    private String tiempo_ejecucion;
    private String observacion_propuesta;

}
