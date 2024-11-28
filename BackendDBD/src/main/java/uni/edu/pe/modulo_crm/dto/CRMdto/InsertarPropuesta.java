package uni.edu.pe.modulo_crm.dto.CRMdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertarPropuesta {
    private String id_presentacion_propuesta;
    private Date fecha_presentacion;
    private float precio_propuesto;
    private String descripcion_tecnica;
    private String descripcion_economica;
    private String calidad_ofrecida;
    private String seguridad_ofrecida;
    private String condicion_pago;
    private String tiempo_ejecucion;
    private String observacion_propuesta;
    private String estado_propuesta;
    private String id_empleado;
    private String id_revision_tecnica;
    private String id_cliente;
}
