package uni.edu.pe.modulo_crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertarPropuesta {
    private String id_presentacion_propuesta;
    private Date fecha_Presentacion;
    private float precio_Propuesto;
    private String descripcion_Tecnica;
    private String descripcion_Economica;
    private String calidad_Ofrecida;
    private String seguridad_Ofrecida;
    private String condicion_Pago;
    private String tiempo_Ejecucion;
    private String observacion_Propuesta;
    private String estado_Propuesta;
    private String id_empleado;
    private String id_revision_tecnica;
    private String id_cliente;
}
